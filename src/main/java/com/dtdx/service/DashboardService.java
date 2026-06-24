package com.dtdx.service;

import com.dtdx.dao.DashboardDao;
import com.dtdx.entity.DashboardMedicationStat;
import com.dtdx.entity.DashboardOverview;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class DashboardService {
    private static final int MEDICATION_TARGET = 90;

    @Autowired
    private DashboardDao dashboardDao;

    public DashboardOverview getOverview() {
        DashboardOverview overview = dashboardDao.getOverview();
        if (overview == null) {
            overview = new DashboardOverview();
        }
        overview.setMedicationTarget(MEDICATION_TARGET);
        overview.setLastUpdateText(new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date()));
        fillWeeklySummary(overview, getMedicationStats());
        return overview;
    }

    public List<DashboardMedicationStat> getMedicationStats() {
        List<DashboardMedicationStat> stats = dashboardDao.listMedicationStats();
        if (stats == null) {
            stats = new ArrayList<DashboardMedicationStat>();
        }
        DashboardMedicationStat previous = null;
        for (DashboardMedicationStat stat : stats) {
            int expected = value(stat.getExpectedCount());
            int completed = value(stat.getCompletedCount());
            int rate = expected == 0 ? 0 : (int) Math.round(completed * 100.0 / expected);
            stat.setExpectedCount(expected);
            stat.setCompletedCount(completed);
            stat.setRate(rate);
            stat.setBarHeight(Math.max(8, Math.min(120, rate)));
            stat.setRateClass(rate >= MEDICATION_TARGET ? "badge-success" : "badge-warning");

            if (previous == null) {
                stat.setChangeText("-");
                stat.setChangeClass("badge-info");
            } else {
                int diff = rate - value(previous.getRate());
                if (diff > 0) {
                    stat.setChangeText("上升 " + diff + "%");
                    stat.setChangeClass("badge-success");
                } else if (diff < 0) {
                    stat.setChangeText("下降 " + Math.abs(diff) + "%");
                    stat.setChangeClass("badge-warning");
                } else {
                    stat.setChangeText("持平");
                    stat.setChangeClass("badge-info");
                }
            }
            previous = stat;
        }
        return stats;
    }

    private void fillWeeklySummary(DashboardOverview overview, List<DashboardMedicationStat> stats) {
        if (stats == null || stats.isEmpty()) {
            overview.setWeeklyAvgRateText("0");
            overview.setWeeklyMaxRateText("0");
            overview.setWeeklyMinRateText("0");
            overview.setWeeklyMaxDateLabel("-");
            overview.setWeeklyMinDateLabel("-");
            overview.setWeeklyTrendText("0%");
            overview.setWeeklyTrendClass("trend-up");
            return;
        }

        int sum = 0;
        DashboardMedicationStat max = stats.get(0);
        DashboardMedicationStat min = stats.get(0);
        for (DashboardMedicationStat stat : stats) {
            int rate = value(stat.getRate());
            sum += rate;
            if (rate > value(max.getRate())) {
                max = stat;
            }
            if (rate < value(min.getRate())) {
                min = stat;
            }
        }

        double avg = sum * 1.0 / stats.size();
        int trend = value(stats.get(stats.size() - 1).getRate()) - value(stats.get(0).getRate());
        DecimalFormat format = new DecimalFormat("0.0");
        overview.setWeeklyAvgRateText(format.format(avg));
        overview.setWeeklyMaxRateText(String.valueOf(value(max.getRate())));
        overview.setWeeklyMinRateText(String.valueOf(value(min.getRate())));
        overview.setWeeklyMaxDateLabel(max.getDateLabel());
        overview.setWeeklyMinDateLabel(min.getDateLabel());
        overview.setWeeklyTrendText((trend >= 0 ? "上升 " : "下降 ") + Math.abs(trend) + "%");
        overview.setWeeklyTrendClass(trend >= 0 ? "trend-up" : "trend-down");
    }

    private int value(Integer value) {
        return value == null ? 0 : value;
    }
}
