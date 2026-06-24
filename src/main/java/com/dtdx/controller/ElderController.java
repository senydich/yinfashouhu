package com.dtdx.controller;

import com.dtdx.entity.Elder;
import com.dtdx.service.ElderService;
import com.dtdx.service.OperationLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/elder")
public class ElderController {

    @Autowired
    private ElderService elderService;

    @Autowired
    private OperationLogService operationLogService;

    @RequestMapping("/elderList")
    public String elderList(Model model, Elder elder) {
        List<Elder> elderList = elderService.elderList(elder);
        model.addAttribute("elderList", elderList);
        return "pages/elder-info";
    }

    @RequestMapping("/add")
    public String add(Elder elder, Model model, HttpServletRequest request) {
        try {
            elderService.add(elder);
            operationLogService.record("elder", "add", "add elder: " + elder.getName(), request);
            return "redirect:/elder/elderList";
        } catch (RuntimeException e) {
            model.addAttribute("elder", elder);
            model.addAttribute("errMsg", e.getMessage());
            return "pages/elder-add";
        }
    }

    @RequestMapping("/addView")
    public String addView() {
        return "pages/elder-add";
    }

    @RequestMapping("/updateView")
    public String updateView(@RequestParam("id") Integer id, Model model) {
        Elder elder = elderService.getById(id);
        if (elder == null) {
            elder = new Elder();
            elder.setId(id);
            model.addAttribute("errMsg", "Elder not found.");
        }
        model.addAttribute("elder", elder);
        return "pages/elder-update";
    }

    @RequestMapping("/update")
    public String update(Elder elder, Model model, HttpServletRequest request) {
        try {
            elderService.update(elder);
            operationLogService.record("elder", "update", "update elder: " + elder.getName(), request);
            return "redirect:/elder/elderList";
        } catch (RuntimeException e) {
            model.addAttribute("elder", elder);
            model.addAttribute("errMsg", e.getMessage());
            return "pages/elder-update";
        }
    }

    @RequestMapping("/del")
    public String del(@RequestParam("id") Integer id, HttpServletRequest request) {
        elderService.del(id);
        operationLogService.record("elder", "delete", "delete elder id: " + id, request);
        return "redirect:/elder/elderList";
    }
}
