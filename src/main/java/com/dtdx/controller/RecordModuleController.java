package com.dtdx.controller;

import com.dtdx.entity.CameraDevice;
import com.dtdx.entity.DialogRecord;
import com.dtdx.entity.EventRecord;
import com.dtdx.entity.FallRecord;
import com.dtdx.entity.AccompanyRecord;
import com.dtdx.entity.Elder;
import com.dtdx.entity.ReminderTask;
import com.dtdx.service.AccompanyRecordService;
import com.dtdx.service.CameraDeviceService;
import com.dtdx.service.DialogRecordService;
import com.dtdx.service.ElderService;
import com.dtdx.service.EventRecordService;
import com.dtdx.service.FallRecordService;
import com.dtdx.service.OperationLogService;
import com.dtdx.service.ReminderTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/record")
public class RecordModuleController {
    @Autowired
    private FallRecordService fallRecordService;

    @Autowired
    private DialogRecordService dialogRecordService;

    @Autowired
    private EventRecordService eventRecordService;

    @Autowired
    private CameraDeviceService cameraDeviceService;

    @Autowired
    private ReminderTaskService reminderTaskService;

    @Autowired
    private AccompanyRecordService accompanyRecordService;

    @Autowired
    private ElderService elderService;

    @Autowired
    private OperationLogService operationLogService;

    @RequestMapping("/fallRecordList")
    public String fallRecordList(Model model, FallRecord fallRecord) {
        model.addAttribute("fallRecordList", fallRecordService.fallRecordList(fallRecord));
        model.addAttribute("query", fallRecord);
        return "pages/fall-record-info";
    }

    @RequestMapping("/eventList")
    public String eventList(Model model, EventRecord eventRecord) {
        model.addAttribute("eventRecordList", eventRecordService.eventRecordList(eventRecord));
        model.addAttribute("query", eventRecord);
        return "pages/event-record-info";
    }

    @RequestMapping("/dialogRecordList")
    public String dialogRecordList(Model model, DialogRecord dialogRecord) {
        model.addAttribute("dialogRecordList", dialogRecordService.dialogRecordList(dialogRecord));
        model.addAttribute("query", dialogRecord);
        return "pages/dialog-record-info";
    }

    @RequestMapping("/cameraDeviceList")
    public String cameraDeviceList(Model model, CameraDevice cameraDevice) {
        model.addAttribute("cameraDeviceList", cameraDeviceService.cameraDeviceList(cameraDevice));
        model.addAttribute("query", cameraDevice);
        return "pages/camera-device-info";
    }

    @RequestMapping("/cameraDeviceAddView")
    public String cameraDeviceAddView(Model model) {
        CameraDevice cameraDevice = new CameraDevice();
        cameraDevice.setStatus("ONLINE");
        model.addAttribute("cameraDevice", cameraDevice);
        model.addAttribute("formAction", "/record/cameraDeviceAdd");
        return "pages/camera-device-form";
    }

    @RequestMapping("/cameraDeviceAdd")
    public String cameraDeviceAdd(CameraDevice cameraDevice, Model model, HttpServletRequest request) {
        try {
            cameraDeviceService.add(cameraDevice);
            operationLogService.record("camera device", "add", "add camera device: " + cameraDevice.getDeviceName(), request);
            return "redirect:/record/cameraDeviceList";
        } catch (RuntimeException e) {
            model.addAttribute("cameraDevice", cameraDevice);
            model.addAttribute("formAction", "/record/cameraDeviceAdd");
            model.addAttribute("errMsg", e.getMessage());
            return "pages/camera-device-form";
        }
    }

    @RequestMapping("/cameraDeviceUpdateView")
    public String cameraDeviceUpdateView(@RequestParam("id") Integer id, Model model) {
        model.addAttribute("cameraDevice", cameraDeviceService.getById(id));
        model.addAttribute("formAction", "/record/cameraDeviceUpdate");
        return "pages/camera-device-form";
    }

    @RequestMapping("/cameraDeviceUpdate")
    public String cameraDeviceUpdate(CameraDevice cameraDevice, Model model, HttpServletRequest request) {
        try {
            cameraDeviceService.update(cameraDevice);
            operationLogService.record("camera device", "update", "update camera device: " + cameraDevice.getDeviceName(), request);
            return "redirect:/record/cameraDeviceList";
        } catch (RuntimeException e) {
            model.addAttribute("cameraDevice", cameraDevice);
            model.addAttribute("formAction", "/record/cameraDeviceUpdate");
            model.addAttribute("errMsg", e.getMessage());
            return "pages/camera-device-form";
        }
    }

    @RequestMapping("/cameraDeviceDel")
    public String cameraDeviceDel(@RequestParam("id") Integer id, HttpServletRequest request) {
        CameraDevice cameraDevice = cameraDeviceService.getById(id);
        cameraDeviceService.delete(id);
        operationLogService.record("camera device", "delete", "delete camera device: " + cameraDevice.getDeviceName(), request);
        return "redirect:/record/cameraDeviceList";
    }

    @RequestMapping("/reminderTaskList")
    public String reminderTaskList(Model model, ReminderTask reminderTask) {
        model.addAttribute("reminderTaskList", reminderTaskService.reminderTaskList(reminderTask));
        model.addAttribute("query", reminderTask);
        return "pages/reminder-task-info";
    }

    @RequestMapping("/reminderTaskAddView")
    public String reminderTaskAddView(Model model) {
        ReminderTask reminderTask = new ReminderTask();
        reminderTask.setTaskType("MEDICATION");
        reminderTask.setRepeatRule("DAILY");
        reminderTask.setStatus("PENDING");
        model.addAttribute("reminderTask", reminderTask);
        model.addAttribute("elderList", elderService.elderList(new Elder()));
        model.addAttribute("formAction", "/record/reminderTaskAdd");
        return "pages/reminder-task-form";
    }

    @RequestMapping("/reminderTaskAdd")
    public String reminderTaskAdd(ReminderTask reminderTask, Model model, HttpServletRequest request) {
        try {
            reminderTaskService.add(reminderTask);
            operationLogService.record("reminder task", "add", "add reminder task: " + reminderTask.getTaskTitle(), request);
            return "redirect:/record/reminderTaskList";
        } catch (RuntimeException e) {
            model.addAttribute("reminderTask", reminderTask);
            model.addAttribute("elderList", elderService.elderList(new Elder()));
            model.addAttribute("formAction", "/record/reminderTaskAdd");
            model.addAttribute("errMsg", e.getMessage());
            return "pages/reminder-task-form";
        }
    }

    @RequestMapping("/reminderTaskUpdateView")
    public String reminderTaskUpdateView(@RequestParam("id") Integer id, Model model) {
        model.addAttribute("reminderTask", reminderTaskService.getById(id));
        model.addAttribute("elderList", elderService.elderList(new Elder()));
        model.addAttribute("formAction", "/record/reminderTaskUpdate");
        return "pages/reminder-task-form";
    }

    @RequestMapping("/reminderTaskUpdate")
    public String reminderTaskUpdate(ReminderTask reminderTask, Model model, HttpServletRequest request) {
        try {
            reminderTaskService.update(reminderTask);
            operationLogService.record("reminder task", "update", "update reminder task: " + reminderTask.getTaskTitle(), request);
            return "redirect:/record/reminderTaskList";
        } catch (RuntimeException e) {
            model.addAttribute("reminderTask", reminderTask);
            model.addAttribute("elderList", elderService.elderList(new Elder()));
            model.addAttribute("formAction", "/record/reminderTaskUpdate");
            model.addAttribute("errMsg", e.getMessage());
            return "pages/reminder-task-form";
        }
    }

    @RequestMapping("/reminderTaskDel")
    public String reminderTaskDel(@RequestParam("id") Integer id, HttpServletRequest request) {
        ReminderTask reminderTask = reminderTaskService.getById(id);
        reminderTaskService.delete(id);
        operationLogService.record("reminder task", "delete", "delete reminder task: " + reminderTask.getTaskTitle(), request);
        return "redirect:/record/reminderTaskList";
    }

    @RequestMapping("/accompanyRecordList")
    public String accompanyRecordList(Model model, AccompanyRecord accompanyRecord) {
        model.addAttribute("accompanyRecordList", accompanyRecordService.accompanyRecordList(accompanyRecord));
        model.addAttribute("query", accompanyRecord);
        return "pages/accompany-record-info";
    }

    @RequestMapping("/accompanyRecordAddView")
    public String accompanyRecordAddView(Model model) {
        AccompanyRecord accompanyRecord = new AccompanyRecord();
        accompanyRecord.setAccompanyType("CHAT");
        accompanyRecord.setEmotionResult("STABLE");
        model.addAttribute("accompanyRecord", accompanyRecord);
        model.addAttribute("elderList", elderService.elderList(new Elder()));
        model.addAttribute("formAction", "/record/accompanyRecordAdd");
        return "pages/accompany-record-form";
    }

    @RequestMapping("/accompanyRecordAdd")
    public String accompanyRecordAdd(AccompanyRecord accompanyRecord, Model model, HttpServletRequest request) {
        try {
            accompanyRecordService.add(accompanyRecord);
            operationLogService.record("accompany record", "add", "add accompany record: " + accompanyRecord.getCompanion(), request);
            return "redirect:/record/accompanyRecordList";
        } catch (RuntimeException e) {
            model.addAttribute("accompanyRecord", accompanyRecord);
            model.addAttribute("elderList", elderService.elderList(new Elder()));
            model.addAttribute("formAction", "/record/accompanyRecordAdd");
            model.addAttribute("errMsg", e.getMessage());
            return "pages/accompany-record-form";
        }
    }

    @RequestMapping("/accompanyRecordUpdateView")
    public String accompanyRecordUpdateView(@RequestParam("id") Integer id, Model model) {
        model.addAttribute("accompanyRecord", accompanyRecordService.getById(id));
        model.addAttribute("elderList", elderService.elderList(new Elder()));
        model.addAttribute("formAction", "/record/accompanyRecordUpdate");
        return "pages/accompany-record-form";
    }

    @RequestMapping("/accompanyRecordUpdate")
    public String accompanyRecordUpdate(AccompanyRecord accompanyRecord, Model model, HttpServletRequest request) {
        try {
            accompanyRecordService.update(accompanyRecord);
            operationLogService.record("accompany record", "update", "update accompany record: " + accompanyRecord.getCompanion(), request);
            return "redirect:/record/accompanyRecordList";
        } catch (RuntimeException e) {
            model.addAttribute("accompanyRecord", accompanyRecord);
            model.addAttribute("elderList", elderService.elderList(new Elder()));
            model.addAttribute("formAction", "/record/accompanyRecordUpdate");
            model.addAttribute("errMsg", e.getMessage());
            return "pages/accompany-record-form";
        }
    }

    @RequestMapping("/accompanyRecordDel")
    public String accompanyRecordDel(@RequestParam("id") Integer id, HttpServletRequest request) {
        AccompanyRecord accompanyRecord = accompanyRecordService.getById(id);
        accompanyRecordService.delete(id);
        operationLogService.record("accompany record", "delete", "delete accompany record: " + accompanyRecord.getCompanion(), request);
        return "redirect:/record/accompanyRecordList";
    }
}
