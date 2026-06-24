package com.dtdx.controller;

import com.dtdx.entity.EdgeGateway;
import com.dtdx.service.EdgeGatewayService;
import com.dtdx.service.OperationLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/record")
public class EdgeGatewayController {
    @Autowired
    private EdgeGatewayService edgeGatewayService;

    @Autowired
    private OperationLogService operationLogService;

    @RequestMapping("/edgeGatewayList")
    public String edgeGatewayList(Model model, EdgeGateway edgeGateway) {
        model.addAttribute("edgeGatewayList", edgeGatewayService.edgeGatewayList(edgeGateway));
        model.addAttribute("query", edgeGateway);
        return "pages/edge-gateway-info";
    }

    @RequestMapping("/edgeGatewayAddView")
    public String edgeGatewayAddView(Model model) {
        EdgeGateway edgeGateway = new EdgeGateway();
        edgeGateway.setAccessProtocol("MQTT");
        edgeGateway.setManagedDeviceCount(0);
        edgeGateway.setStatus("ONLINE");
        model.addAttribute("edgeGateway", edgeGateway);
        model.addAttribute("formAction", "/record/edgeGatewayAdd");
        return "pages/edge-gateway-form";
    }

    @RequestMapping("/edgeGatewayAdd")
    public String edgeGatewayAdd(EdgeGateway edgeGateway, Model model, HttpServletRequest request) {
        try {
            edgeGatewayService.add(edgeGateway);
            operationLogService.record("edge gateway", "add", "add edge gateway: " + edgeGateway.getGatewayCode(), request);
            return "redirect:/record/edgeGatewayList";
        } catch (RuntimeException e) {
            model.addAttribute("edgeGateway", edgeGateway);
            model.addAttribute("formAction", "/record/edgeGatewayAdd");
            model.addAttribute("errMsg", e.getMessage());
            return "pages/edge-gateway-form";
        }
    }

    @RequestMapping("/edgeGatewayUpdateView")
    public String edgeGatewayUpdateView(@RequestParam("id") Integer id, Model model) {
        model.addAttribute("edgeGateway", edgeGatewayService.getById(id));
        model.addAttribute("formAction", "/record/edgeGatewayUpdate");
        return "pages/edge-gateway-form";
    }

    @RequestMapping("/edgeGatewayUpdate")
    public String edgeGatewayUpdate(EdgeGateway edgeGateway, Model model, HttpServletRequest request) {
        try {
            edgeGatewayService.update(edgeGateway);
            operationLogService.record("edge gateway", "update", "update edge gateway: " + edgeGateway.getGatewayCode(), request);
            return "redirect:/record/edgeGatewayList";
        } catch (RuntimeException e) {
            model.addAttribute("edgeGateway", edgeGateway);
            model.addAttribute("formAction", "/record/edgeGatewayUpdate");
            model.addAttribute("errMsg", e.getMessage());
            return "pages/edge-gateway-form";
        }
    }

    @RequestMapping("/edgeGatewayDel")
    public String edgeGatewayDel(@RequestParam("id") Integer id, HttpServletRequest request) {
        EdgeGateway edgeGateway = edgeGatewayService.getById(id);
        edgeGatewayService.delete(id);
        operationLogService.record("edge gateway", "delete", "delete edge gateway: " + edgeGateway.getGatewayCode(), request);
        return "redirect:/record/edgeGatewayList";
    }
}
