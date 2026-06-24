package com.dtdx.service;

import com.dtdx.dao.DailyReportDao;
import com.dtdx.entity.BehaviorRecord;
import com.dtdx.entity.DailyReport;
import com.dtdx.entity.Elder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class DailyReportService {

    @Autowired
    private DailyReportDao dailyReportDao;

    @Autowired
    private BehaviorRecordService behaviorRecordService;

    @Autowired
    private ElderService elderService;

    public List<DailyReport> dailyReportList(DailyReport dailyReport) {
        return dailyReportDao.dailyReportList(dailyReport);
    }

    public DailyReport getById(Integer id) {
        if (id == null) {
            throw new RuntimeException("报告ID不能为空");
        }
        DailyReport report = dailyReportDao.getById(id);
        if (report == null) {
            throw new RuntimeException("报告不存在");
        }
        return report;
    }

    public DailyReport generate(Integer elderId, Date reportDate) {
        if (elderId == null) {
            throw new RuntimeException("请选择老人");
        }
        if (reportDate == null) {
            throw new RuntimeException("请选择报告日期");
        }
        Elder elder = elderService.getById(elderId);
        if (elder == null) {
            throw new RuntimeException("老人不存在");
        }

        List<BehaviorRecord> records = behaviorRecordService.findByElderAndDate(elderId, reportDate);
        DailyReport report = dailyReportDao.getByElderAndDate(elderId, reportDate);
        if (report == null) {
            report = new DailyReport();
            report.setElderId(elderId);
            report.setReportDate(reportDate);
        }
        fillReport(report, elder, records);
        if (report.getId() == null) {
            dailyReportDao.add(report);
        } else {
            dailyReportDao.update(report);
        }
        return report;
    }

    public void delete(Integer id) {
        getById(id);
        dailyReportDao.delete(id);
    }

    private void fillReport(DailyReport report, Elder elder, List<BehaviorRecord> records) {
        int activityMinutes = 0;
        int sleepMinutes = 0;
        int medicationCount = 0;
        int abnormalCount = 0;
        int highRiskCount = 0;

        for (BehaviorRecord record : records) {
            int duration = record.getDurationMinutes() == null ? 0 : record.getDurationMinutes();
            if ("ACTIVITY".equals(record.getBehaviorType())) {
                activityMinutes += duration;
            } else if ("SLEEP".equals(record.getBehaviorType())) {
                sleepMinutes += duration;
            } else if ("MEDICATION".equals(record.getBehaviorType())) {
                medicationCount++;
            } else if ("ABNORMAL".equals(record.getBehaviorType())) {
                abnormalCount++;
            }
            if ("HIGH".equals(record.getRiskLevel())) {
                highRiskCount++;
            }
        }

        String riskLevel = abnormalCount > 0 || highRiskCount > 0 ? "HIGH" : (activityMinutes < 30 ? "MEDIUM" : "LOW");
        report.setElderName(elder.getName());
        report.setTotalRecords(records.size());
        report.setActivityMinutes(activityMinutes);
        report.setSleepMinutes(sleepMinutes);
        report.setMedicationCount(medicationCount);
        report.setAbnormalCount(abnormalCount);
        report.setRiskLevel(riskLevel);
        report.setStatus(1);
        report.setSummary(buildSummary(elder, report, records));
        report.setSuggestion(buildSuggestion(report, records));
    }

    private String buildSummary(Elder elder, DailyReport report, List<BehaviorRecord> records) {
        String base = elder.getName() + "在" + formatDate(report.getReportDate())
                + "共记录" + report.getTotalRecords() + "条行为，活动"
                + report.getActivityMinutes() + "分钟，睡眠"
                + report.getSleepMinutes() + "分钟，用药"
                + report.getMedicationCount() + "次，异常"
                + report.getAbnormalCount() + "次。";
        String detail = buildBehaviorDetail(records);
        if (!"".equals(detail)) {
            return base + "主要行为：" + detail + "。";
        }
        return base;
    }

    private String buildSuggestion(DailyReport report, List<BehaviorRecord> records) {
        if ("HIGH".equals(report.getRiskLevel())) {
            return "建议护理人员重点关注异常行为，必要时联系家属或医生复核。";
        }
        if ("MEDIUM".equals(report.getRiskLevel())) {
            return "建议增加日间活动观察，关注久坐、少动或作息异常。";
        }
        if (records != null && !records.isEmpty()) {
            return "整体行为稳定，继续保持对" + buildBehaviorDetail(records) + "等行为的日常观察。";
        }
        return "当天暂无行为记录，继续保持日常观察和健康提醒。";
    }

    private String buildBehaviorDetail(List<BehaviorRecord> records) {
        if (records == null || records.isEmpty()) {
            return "";
        }
        StringBuilder builder = new StringBuilder();
        int limit = 3;
        for (BehaviorRecord record : records) {
            if (record == null || record.getBehaviorName() == null || "".equals(record.getBehaviorName().trim())) {
                continue;
            }
            if (builder.length() > 0) {
                builder.append("、");
            }
            builder.append(record.getBehaviorName().trim());
            limit--;
            if (limit == 0) {
                break;
            }
        }
        return builder.toString();
    }

    private String formatDate(Date date) {
        return new SimpleDateFormat("yyyy-MM-dd").format(date);
    }
}
