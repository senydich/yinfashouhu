package com.dtdx.controller;

import com.dtdx.entity.OperationLog;
import com.dtdx.service.OperationLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/operationLog")
public class OperationLogController {

    @Autowired
    private OperationLogService operationLogService;

    @RequestMapping("/operationLogList")
    public String operationLogList(Model model, OperationLog operationLog) {
        List<OperationLog> operationLogList = operationLogService.operationLogList(operationLog);
        model.addAttribute("operationLogList", operationLogList);
        return "pages/operation-log-info";
    }

    @RequestMapping("/del")
    public String del(@RequestParam("id") Integer id, HttpServletRequest request) {
        operationLogService.delete(id);
        return "redirect:/operationLog/operationLogList";
    }
}
