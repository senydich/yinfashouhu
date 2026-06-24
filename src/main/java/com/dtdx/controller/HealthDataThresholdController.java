package com.dtdx.controller;

import com.dtdx.entity.HealthDataThreshold;
import com.dtdx.service.HealthDataThresholdService;
import com.dtdx.service.OperationLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/hdt")
public class HealthDataThresholdController {
    @Autowired
    private HealthDataThresholdService healthDataThresholdService;

    @Autowired
    private OperationLogService operationLogService;

    @RequestMapping("/getOne")
    public String getOne(Model model) {
        HealthDataThreshold healthDataThreshold = healthDataThresholdService.getOne();
        model.addAttribute("hdt", healthDataThreshold);
        return "pages/threshold";
    }

    @RequestMapping("/update")
    public String update(HealthDataThreshold healthDataThreshold, HttpServletRequest request) {
        healthDataThresholdService.update(healthDataThreshold);
        operationLogService.record("health threshold", "update", "update health threshold", request);
        return "redirect:/hdt/getOne";
    }
}
