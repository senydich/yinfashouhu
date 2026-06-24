package com.dtdx.controller;

import com.dtdx.entity.Elder;
import com.dtdx.entity.HealthData;
import com.dtdx.entity.HealthDataThreshold;
import com.dtdx.service.ElderService;
import com.dtdx.service.HealthDataService;
import com.dtdx.service.HealthDataThresholdService;
import com.dtdx.service.OperationLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/healthData")
public class HealthDataController {
    @Autowired
    private HealthDataService healthDataService;

    @Autowired
    private ElderService elderService;

    @Autowired
    private OperationLogService operationLogService;

    @Autowired
    private HealthDataThresholdService healthDataThresholdService;

    @RequestMapping("/list")
    public String list(Model model, HealthData healthData) {
        if (healthData.getPageNum() == null || healthData.getPageNum() < 1) {
            healthData.setPageNum(1);
        }
        if (healthData.getPageSize() == null || healthData.getPageSize() < 1) {
            healthData.setPageSize(100);
        }
        Integer total = healthDataService.count(healthData);
        int totalPages = healthDataService.getTotalPages(total, healthData.getPageSize());
        if (healthData.getPageNum() > totalPages) {
            healthData.setPageNum(totalPages);
        }

        List<HealthData> healthDataList = healthDataService.healthDataList(healthData);
        HealthDataThreshold threshold = healthDataThresholdService.getOne();
        model.addAttribute("healthDataList", healthDataList);
        model.addAttribute("hdt", threshold);
        model.addAttribute("query", healthData);
        model.addAttribute("total", total);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("currentPage", healthData.getPageNum());
        model.addAttribute("todayStr", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        model.addAttribute("todayCount", healthDataList.size());
        model.addAttribute("abnormalCount", countAbnormal(healthDataList, threshold));
        model.addAttribute("bpAbnormal", countBloodPressureAbnormal(healthDataList, threshold));
        model.addAttribute("glucoseAbnormal", countBloodGlucoseAbnormal(healthDataList, threshold));
        return "pages/health-data-info";
    }

    @RequestMapping("/addView")
    public String addView(Model model) {
        model.addAttribute("healthData", new HealthData());
        model.addAttribute("elderList", elderService.elderList(new Elder()));
        model.addAttribute("formAction", "/healthData/add");
        return "pages/health-data-form";
    }

    @RequestMapping("/add")
    public String add(HealthData healthData, Model model, HttpServletRequest request) {
        try {
            healthDataService.add(healthData);
            operationLogService.record("health data", "add", "add health data: elderId=" + healthData.getElderId(), request);
            return "redirect:/healthData/list";
        } catch (RuntimeException e) {
            model.addAttribute("healthData", healthData);
            model.addAttribute("elderList", elderService.elderList(new Elder()));
            model.addAttribute("formAction", "/healthData/add");
            model.addAttribute("errMsg", e.getMessage());
            return "pages/health-data-form";
        }
    }

    @RequestMapping("/updateView")
    public String updateView(@RequestParam("id") Integer id, Model model) {
        model.addAttribute("healthData", healthDataService.getById(id));
        model.addAttribute("elderList", elderService.elderList(new Elder()));
        model.addAttribute("formAction", "/healthData/update");
        return "pages/health-data-form";
    }

    @RequestMapping("/update")
    public String update(HealthData healthData, Model model, HttpServletRequest request) {
        try {
            healthDataService.update(healthData);
            operationLogService.record("health data", "update", "update health data: id=" + healthData.getId(), request);
            return "redirect:/healthData/list";
        } catch (RuntimeException e) {
            model.addAttribute("healthData", healthData);
            model.addAttribute("elderList", elderService.elderList(new Elder()));
            model.addAttribute("formAction", "/healthData/update");
            model.addAttribute("errMsg", e.getMessage());
            return "pages/health-data-form";
        }
    }

    @RequestMapping("/delete")
    public String delete(@RequestParam("id") Integer id, HttpServletRequest request) {
        HealthData healthData = healthDataService.getById(id);
        healthDataService.delete(id);
        operationLogService.record("health data", "delete", "delete health data: id=" + healthData.getId(), request);
        return "redirect:/healthData/list";
    }

    private int countAbnormal(List<HealthData> healthDataList, HealthDataThreshold threshold) {
        int count = 0;
        for (HealthData item : healthDataList) {
            if (isBloodPressureAbnormal(item, threshold)
                    || item.getBloodGlucose() != null && (item.getBloodGlucose() > doubleValue(threshold == null ? null : threshold.getBloodGlucoseHigh(), 7.0)
                    || item.getBloodGlucose() < doubleValue(threshold == null ? null : threshold.getBloodGlucoseLow(), 0.0))
                    || item.getHeartRate() != null && (item.getHeartRate() > intValue(threshold == null ? null : threshold.getHeartRatehigh(), 100)
                    || item.getHeartRate() < intValue(threshold == null ? null : threshold.getHeartRatelow(), 60))
                    || item.getSpo2() != null && item.getSpo2() < intValue(threshold == null ? null : threshold.getSpo2(), 95)
                    || item.getTemperature() != null && (item.getTemperature() > doubleValue(threshold == null ? null : threshold.getTemperatureHigh(), 37.5)
                    || item.getTemperature() < doubleValue(threshold == null ? null : threshold.getTemperatureLow(), 35.5))
                    || isWeightAbnormal(item, healthDataList, threshold)) {
                count++;
            }
        }
        return count;
    }

    private int countBloodPressureAbnormal(List<HealthData> healthDataList, HealthDataThreshold threshold) {
        int count = 0;
        for (HealthData item : healthDataList) {
            if (isBloodPressureAbnormal(item, threshold)) {
                count++;
            }
        }
        return count;
    }

    private int countBloodGlucoseAbnormal(List<HealthData> healthDataList, HealthDataThreshold threshold) {
        int count = 0;
        for (HealthData item : healthDataList) {
            if (item.getBloodGlucose() != null
                    && (item.getBloodGlucose() > doubleValue(threshold == null ? null : threshold.getBloodGlucoseHigh(), 7.0)
                    || item.getBloodGlucose() < doubleValue(threshold == null ? null : threshold.getBloodGlucoseLow(), 0.0))) {
                count++;
            }
        }
        return count;
    }

    private boolean isBloodPressureAbnormal(HealthData healthData, HealthDataThreshold threshold) {
        if (healthData == null || healthData.getBloodPressure() == null || !healthData.getBloodPressure().contains("/")) {
            return false;
        }
        String[] parts = healthData.getBloodPressure().split("/");
        if (parts.length != 2) {
            return false;
        }
        try {
            int systolic = Integer.parseInt(parts[0].trim());
            int diastolic = Integer.parseInt(parts[1].trim());
            return systolic > doubleValue(threshold == null ? null : threshold.getBloodPressureHigh(), 140.0)
                    || diastolic > doubleValue(threshold == null ? null : threshold.getBloodPressureLow(), 90.0);
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean isWeightAbnormal(HealthData current, List<HealthData> healthDataList, HealthDataThreshold threshold) {
        if (current == null || current.getWeight() == null || current.getElderId() == null || current.getRecordTime() == null) {
            return false;
        }
        HealthData previous = null;
        for (HealthData item : healthDataList) {
            if (item == null || item.getWeight() == null || item.getElderId() == null || item.getRecordTime() == null) {
                continue;
            }
            if (!current.getElderId().equals(item.getElderId()) || !item.getRecordTime().before(current.getRecordTime())) {
                continue;
            }
            if (previous == null || item.getRecordTime().after(previous.getRecordTime())) {
                previous = item;
            }
        }
        if (previous == null) {
            return false;
        }
        return Math.abs(current.getWeight() - previous.getWeight()) > doubleValue(threshold == null ? null : threshold.getWeight(), 2.0);
    }

    private double doubleValue(Double value, double defaultValue) {
        return value == null ? defaultValue : value;
    }

    private int intValue(Integer value, int defaultValue) {
        return value == null ? defaultValue : value;
    }
}
