package com.dtdx.controller;

import com.dtdx.entity.EventRecord;
import com.dtdx.entity.User;
import com.dtdx.service.DashboardService;
import com.dtdx.service.EventRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
public class FrameController {
    @Autowired
    private DashboardService dashboardService;

    @Autowired
    private EventRecordService eventRecordService;

    @RequestMapping("/main")
    public String mainpage() {
        return "main";
    }

    @RequestMapping("/top")
    public String top(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        Object loginUser = session == null ? null : session.getAttribute("loginUser");

        if (loginUser instanceof User) {
            User user = (User) loginUser;
            String displayName = user.getRealName();
            if (displayName == null || "".equals(displayName)) {
                displayName = user.getLoginName();
            }

            String roleName = firstString(session == null ? null : session.getAttribute("roleNames"));
            if (roleName == null || "".equals(roleName)) {
                roleName = user.getJob();
            }
            if (roleName == null || "".equals(roleName)) {
                roleName = "\u7528\u6237";
            }

            model.addAttribute("currentUserName", displayName);
            model.addAttribute("currentUserRole", roleName);
        } else {
            model.addAttribute("currentUserName", "\u672a\u767b\u5f55");
            model.addAttribute("currentUserRole", "\u7528\u6237");
        }
        appendNotificationModel(model);
        return "top";
    }

    private void appendNotificationModel(Model model) {
        List<EventRecord> latestEvents = new ArrayList<EventRecord>();
        int unreadCount = 0;
        int highCount = 0;
        int pendingCount = 0;

        try {
            List<EventRecord> events = eventRecordService.eventRecordList(new EventRecord());
            if (events != null) {
                for (EventRecord event : events) {
                    if ("HIGH".equals(event.getEventLevel())) {
                        highCount++;
                    }
                    if ("PENDING".equals(event.getEventStatus())) {
                        pendingCount++;
                    }
                    if ("HIGH".equals(event.getEventLevel()) || "PENDING".equals(event.getEventStatus())) {
                        unreadCount++;
                    }
                    if (latestEvents.size() < 8) {
                        latestEvents.add(event);
                    }
                }
            }
        } catch (Exception e) {
            latestEvents = new ArrayList<EventRecord>();
        }

        model.addAttribute("notificationList", latestEvents);
        model.addAttribute("notificationUnreadCount", unreadCount);
        model.addAttribute("notificationHighCount", highCount);
        model.addAttribute("notificationPendingCount", pendingCount);
    }

    @RequestMapping("/left")
    public String left(Model model, HttpServletRequest request) {
        appendPermissionModel(model, request);
        return "left";
    }

    @RequestMapping("/dashboard")
    public String dashboard(Model model, HttpServletRequest request) {
        model.addAttribute("overview", dashboardService.getOverview());
        model.addAttribute("medicationStats", dashboardService.getMedicationStats());
        model.addAttribute("dashboardEvents", latestEvents(6));
        appendPermissionModel(model, request);
        return "pages/dashboard";
    }

    @RequestMapping("/healthData")
    public String healthData() {
        return "redirect:/healthData/list";
    }

    @RequestMapping("/pages/health-data.html")
    public String healthDataHtml() {
        return "redirect:/healthData/list";
    }

    @RequestMapping("/pages/camera-info.html")
    public String cameraInfoHtml() {
        return "pages/camera-info";
    }

    @RequestMapping("/pages/voice-terminal-info.html")
    public String voiceTerminalInfoHtml() {
        return "redirect:/record/voiceTerminalList";
    }

    @RequestMapping("/pages/edge-gateway-info.html")
    public String edgeGatewayInfoHtml() {
        return "redirect:/record/edgeGatewayList";
    }

    @RequestMapping("/pages/chat.html")
    public String chatHtml() {
        return "pages/chat";
    }

    @RequestMapping("/pages/threshold.html")
    public String thresholdHtml() {
        return "redirect:/hdt/getOne";
    }

    @RequestMapping("/pages/behavior-record-info.html")
    public String behaviorRecordHtml() {
        return "redirect:/record/fallRecordList";
    }

    @RequestMapping("/pages/daily-report-info.html")
    public String dailyReportHtml() {
        return "redirect:/report/dailyReportList";
    }

    @RequestMapping("/pages/checkup-record-info.html")
    public String checkupRecordHtml() {
        return "redirect:/report/checkupRecordList";
    }

    @RequestMapping("/report/accompanyRecordList")
    public String accompanyRecordAlias() {
        return "redirect:/record/accompanyRecordList";
    }

    @RequestMapping("/log/operationLogList")
    public String operationLogAlias() {
        return "redirect:/operationLog/operationLogList";
    }

    @RequestMapping("/noPermission")
    public String noPermission() {
        return "pages/no-permission";
    }

    private void appendPermissionModel(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        Set<String> permissionUrls = toStringSet(session == null ? null : session.getAttribute("permissionUrls"));
        boolean superAdmin = isSuperAdmin(session);

        model.addAttribute("canDashboard", true);
        model.addAttribute("canElderList", canAccess(superAdmin, permissionUrls, "/elder/elderList"));
        model.addAttribute("canFallRecord", canAccess(superAdmin, permissionUrls, "/record/fallRecordList"));
        model.addAttribute("canHealthData", canAccess(superAdmin, permissionUrls, "/healthData/list") || canAccess(superAdmin, permissionUrls, "/healthData"));
        model.addAttribute("canCheckupRecord", canAccess(superAdmin, permissionUrls, "/report/checkupRecordList"));
        model.addAttribute("canReminderTask", canAccess(superAdmin, permissionUrls, "/record/reminderTaskList"));
        model.addAttribute("canEventList", canAccess(superAdmin, permissionUrls, "/record/eventList"));
        model.addAttribute("canDialogRecord", canAccess(superAdmin, permissionUrls, "/record/dialogRecordList"));
        model.addAttribute("canCameraDevice", canAccess(superAdmin, permissionUrls, "/record/cameraDeviceList"));
        model.addAttribute("canCameraMonitor", canAccess(superAdmin, permissionUrls, "/pages/camera-info.html"));
        model.addAttribute("canVoiceTerminal", canAccess(superAdmin, permissionUrls, "/record/voiceTerminalList"));
        model.addAttribute("canEdgeGateway", canAccess(superAdmin, permissionUrls, "/record/edgeGatewayList"));
        model.addAttribute("canChat", canAccess(superAdmin, permissionUrls, "/pages/chat.html"));
        model.addAttribute("canAccompanyRecord", canAccess(superAdmin, permissionUrls, "/record/accompanyRecordList"));
        model.addAttribute("canDailyReport", canAccess(superAdmin, permissionUrls, "/report/dailyReportList"));
        model.addAttribute("canBehaviorRecord", canAccess(superAdmin, permissionUrls, "/report/behaviorRecordList"));
        model.addAttribute("canThreshold", canAccess(superAdmin, permissionUrls, "/hdt/getOne"));
        model.addAttribute("canRole", canAccess(superAdmin, permissionUrls, "/role/roleList"));
        model.addAttribute("canUser", canAccess(superAdmin, permissionUrls, "/user/userList"));
        model.addAttribute("canOperationLog", canAccess(superAdmin, permissionUrls, "/operationLog/operationLogList") || canAccess(superAdmin, permissionUrls, "/log/operationLogList"));
        model.addAttribute("canDictionary", canAccess(superAdmin, permissionUrls, "/dictionary/dictionaryList"));
    }

    private List<EventRecord> latestEvents(int limit) {
        List<EventRecord> latestEvents = new ArrayList<EventRecord>();
        try {
            List<EventRecord> events = eventRecordService.eventRecordList(new EventRecord());
            if (events != null) {
                for (EventRecord event : events) {
                    if (latestEvents.size() >= limit) {
                        break;
                    }
                    latestEvents.add(event);
                }
            }
        } catch (Exception e) {
            return latestEvents;
        }
        return latestEvents;
    }

    private String firstString(Object value) {
        if (!(value instanceof Collection)) {
            return null;
        }
        Collection<?> values = (Collection<?>) value;
        for (Object item : values) {
            if (item != null && !"".equals(String.valueOf(item).trim())) {
                return String.valueOf(item);
            }
        }
        return null;
    }

    private boolean isSuperAdmin(HttpSession session) {
        if (session == null) {
            return false;
        }
        Object loginUser = session.getAttribute("loginUser");
        if (loginUser instanceof User && "admin".equalsIgnoreCase(((User) loginUser).getLoginName())) {
            return true;
        }
        Set<String> roleCodes = toStringSet(session.getAttribute("roleCodes"));
        for (String roleCode : roleCodes) {
            if ("admin".equalsIgnoreCase(roleCode)) {
                return true;
            }
        }
        return false;
    }

    private Set<String> toStringSet(Object value) {
        if (!(value instanceof Collection)) {
            return Collections.emptySet();
        }
        Set<String> result = new HashSet<String>();
        Collection<?> values = (Collection<?>) value;
        for (Object item : values) {
            if (item != null && !"".equals(String.valueOf(item).trim())) {
                result.add(String.valueOf(item).trim());
            }
        }
        return result;
    }

    private boolean canAccess(boolean superAdmin, Set<String> permissionUrls, String uri) {
        if (superAdmin) {
            return true;
        }
        if (permissionUrls == null || permissionUrls.isEmpty()) {
            return false;
        }
        if (permissionUrls.contains(uri)) {
            return true;
        }
        for (String permissionUrl : permissionUrls) {
            if (matchesWildcard(uri, permissionUrl)) {
                return true;
            }
        }
        return false;
    }

    private boolean matchesWildcard(String uri, String permissionUrl) {
        if (permissionUrl == null) {
            return false;
        }
        if (permissionUrl.endsWith("/**")) {
            String prefix = permissionUrl.substring(0, permissionUrl.length() - 3);
            return uri.equals(prefix) || uri.startsWith(prefix + "/");
        }
        if (permissionUrl.endsWith("/*")) {
            String prefix = permissionUrl.substring(0, permissionUrl.length() - 2);
            return uri.startsWith(prefix + "/") && uri.indexOf('/', prefix.length() + 1) < 0;
        }
        return false;
    }
}
