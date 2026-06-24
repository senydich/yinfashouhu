package com.dtdx.controller;

import com.dtdx.entity.CheckupRecord;
import com.dtdx.entity.Elder;
import com.dtdx.service.CheckupRecordService;
import com.dtdx.service.ElderService;
import com.dtdx.service.OperationLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/report")
public class CheckupRecordController {
    @Autowired
    private CheckupRecordService checkupRecordService;

    @Autowired
    private ElderService elderService;

    @Autowired
    private OperationLogService operationLogService;

    @RequestMapping("/checkupRecordList")
    public String checkupRecordList(Model model, CheckupRecord checkupRecord) {
        model.addAttribute("checkupRecordList", checkupRecordService.checkupRecordList(checkupRecord));
        model.addAttribute("query", checkupRecord);
        return "pages/checkup-record-info";
    }

    @RequestMapping("/checkupRecordAddView")
    public String checkupRecordAddView(Model model) {
        CheckupRecord checkupRecord = new CheckupRecord();
        checkupRecord.setCheckupType("ROUTINE");
        checkupRecord.setRiskLevel("LOW");
        checkupRecord.setStatus("NORMAL");
        model.addAttribute("checkupRecord", checkupRecord);
        model.addAttribute("elderList", elderService.elderList(new Elder()));
        model.addAttribute("formAction", "/report/checkupRecordAdd");
        return "pages/checkup-record-form";
    }

    @RequestMapping("/checkupRecordAdd")
    public String checkupRecordAdd(CheckupRecord checkupRecord, Model model, HttpServletRequest request) {
        try {
            checkupRecordService.add(checkupRecord);
            operationLogService.record("checkup record", "add", "add checkup record: " + checkupRecord.getResultSummary(), request);
            return "redirect:/report/checkupRecordList";
        } catch (RuntimeException e) {
            model.addAttribute("checkupRecord", checkupRecord);
            model.addAttribute("elderList", elderService.elderList(new Elder()));
            model.addAttribute("formAction", "/report/checkupRecordAdd");
            model.addAttribute("errMsg", e.getMessage());
            return "pages/checkup-record-form";
        }
    }

    @RequestMapping("/checkupRecordUpdateView")
    public String checkupRecordUpdateView(@RequestParam("id") Integer id, Model model) {
        model.addAttribute("checkupRecord", checkupRecordService.getById(id));
        model.addAttribute("elderList", elderService.elderList(new Elder()));
        model.addAttribute("formAction", "/report/checkupRecordUpdate");
        return "pages/checkup-record-form";
    }

    @RequestMapping("/checkupRecordUpdate")
    public String checkupRecordUpdate(CheckupRecord checkupRecord, Model model, HttpServletRequest request) {
        try {
            checkupRecordService.update(checkupRecord);
            operationLogService.record("checkup record", "update", "update checkup record: " + checkupRecord.getResultSummary(), request);
            return "redirect:/report/checkupRecordList";
        } catch (RuntimeException e) {
            model.addAttribute("checkupRecord", checkupRecord);
            model.addAttribute("elderList", elderService.elderList(new Elder()));
            model.addAttribute("formAction", "/report/checkupRecordUpdate");
            model.addAttribute("errMsg", e.getMessage());
            return "pages/checkup-record-form";
        }
    }

    @RequestMapping("/checkupRecordDel")
    public String checkupRecordDel(@RequestParam("id") Integer id, HttpServletRequest request) {
        CheckupRecord record = checkupRecordService.getById(id);
        checkupRecordService.delete(id);
        operationLogService.record("checkup record", "delete", "delete checkup record: " + record.getResultSummary(), request);
        return "redirect:/report/checkupRecordList";
    }
}
