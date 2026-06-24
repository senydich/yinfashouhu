package com.dtdx.controller;

import com.dtdx.entity.BehaviorRecord;
import com.dtdx.entity.DailyReport;
import com.dtdx.entity.Elder;
import com.dtdx.service.BehaviorRecordService;
import com.dtdx.service.DailyReportService;
import com.dtdx.service.ElderService;
import com.dtdx.service.OperationLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/report")
public class BehaviorReportController {

    @Autowired
    private BehaviorRecordService behaviorRecordService;

    @Autowired
    private DailyReportService dailyReportService;

    @Autowired
    private ElderService elderService;

    @Autowired
    private OperationLogService operationLogService;

    @RequestMapping("/behaviorRecordList")
    public String behaviorRecordList(Model model, BehaviorRecord behaviorRecord) {
        model.addAttribute("behaviorRecordList", behaviorRecordService.behaviorRecordList(behaviorRecord));
        model.addAttribute("query", behaviorRecord);
        return "pages/behavior-record-info";
    }

    @RequestMapping("/behaviorRecordAddView")
    public String behaviorRecordAddView(Model model) {
        BehaviorRecord record = new BehaviorRecord();
        record.setRiskLevel("LOW");
        model.addAttribute("behaviorRecord", record);
        model.addAttribute("elderList", elderService.elderList(new Elder()));
        model.addAttribute("formAction", "/report/behaviorRecordAdd");
        return "pages/behavior-record-form";
    }

    @RequestMapping("/behaviorRecordAdd")
    public String behaviorRecordAdd(BehaviorRecord behaviorRecord, Model model, HttpServletRequest request) {
        try {
            behaviorRecordService.add(behaviorRecord);
            operationLogService.record("behavior report", "add", "add behavior record: " + behaviorRecord.getBehaviorName(), request);
            return "redirect:/report/behaviorRecordList";
        } catch (RuntimeException e) {
            model.addAttribute("behaviorRecord", behaviorRecord);
            model.addAttribute("elderList", elderService.elderList(new Elder()));
            model.addAttribute("formAction", "/report/behaviorRecordAdd");
            model.addAttribute("errMsg", e.getMessage());
            return "pages/behavior-record-form";
        }
    }

    @RequestMapping("/behaviorRecordUpdateView")
    public String behaviorRecordUpdateView(@RequestParam("id") Integer id, Model model) {
        model.addAttribute("behaviorRecord", behaviorRecordService.getById(id));
        model.addAttribute("elderList", elderService.elderList(new Elder()));
        model.addAttribute("formAction", "/report/behaviorRecordUpdate");
        return "pages/behavior-record-form";
    }

    @RequestMapping("/behaviorRecordUpdate")
    public String behaviorRecordUpdate(BehaviorRecord behaviorRecord, Model model, HttpServletRequest request) {
        try {
            behaviorRecordService.update(behaviorRecord);
            operationLogService.record("behavior report", "update", "update behavior record: " + behaviorRecord.getBehaviorName(), request);
            return "redirect:/report/behaviorRecordList";
        } catch (RuntimeException e) {
            model.addAttribute("behaviorRecord", behaviorRecord);
            model.addAttribute("elderList", elderService.elderList(new Elder()));
            model.addAttribute("formAction", "/report/behaviorRecordUpdate");
            model.addAttribute("errMsg", e.getMessage());
            return "pages/behavior-record-form";
        }
    }

    @RequestMapping("/behaviorRecordDel")
    public String behaviorRecordDel(@RequestParam("id") Integer id, Model model, HttpServletRequest request) {
        try {
            BehaviorRecord record = behaviorRecordService.getById(id);
            behaviorRecordService.delete(id);
            operationLogService.record("behavior report", "delete", "delete behavior record: " + record.getBehaviorName(), request);
            return "redirect:/report/behaviorRecordList";
        } catch (RuntimeException e) {
            model.addAttribute("behaviorRecordList", behaviorRecordService.behaviorRecordList(new BehaviorRecord()));
            model.addAttribute("query", new BehaviorRecord());
            model.addAttribute("errMsg", e.getMessage());
            return "pages/behavior-record-info";
        }
    }

    @RequestMapping("/dailyReportList")
    public String dailyReportList(Model model, DailyReport dailyReport) {
        fillDailyReportPage(model, dailyReport);
        return "pages/daily-report-info";
    }

    @RequestMapping("/generateDailyReport")
    public String generateDailyReport(@RequestParam("elderId") Integer elderId,
                                      @RequestParam("reportDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date reportDate,
                                      Model model,
                                      HttpServletRequest request) {
        try {
            DailyReport report = dailyReportService.generate(elderId, reportDate);
            operationLogService.record("behavior report", "generate", "generate daily report id: " + report.getId(), request);
            return "redirect:/report/dailyReportList";
        } catch (RuntimeException e) {
            fillDailyReportPage(model, new DailyReport());
            model.addAttribute("errMsg", e.getMessage());
            return "pages/daily-report-info";
        }
    }

    @RequestMapping("/dailyReportDel")
    public String dailyReportDel(@RequestParam("id") Integer id, Model model, HttpServletRequest request) {
        try {
            dailyReportService.delete(id);
            operationLogService.record("behavior report", "delete", "delete daily report id: " + id, request);
            return "redirect:/report/dailyReportList";
        } catch (RuntimeException e) {
            fillDailyReportPage(model, new DailyReport());
            model.addAttribute("errMsg", e.getMessage());
            return "pages/daily-report-info";
        }
    }

    private void fillDailyReportPage(Model model, DailyReport dailyReport) {
        model.addAttribute("dailyReportList", dailyReportService.dailyReportList(dailyReport));
        model.addAttribute("elderList", elderService.elderList(new Elder()));
        model.addAttribute("query", dailyReport);
    }
}
