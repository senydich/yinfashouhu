package com.dtdx.controller;

import com.dtdx.entity.Permission;
import com.dtdx.service.OperationLogService;
import com.dtdx.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/permission")
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    @Autowired
    private OperationLogService operationLogService;

    @RequestMapping("/permissionList")
    public String permissionList(Model model, Permission permission) {
        model.addAttribute("permissionList", permissionService.permissionList(permission));
        return "pages/permission-info";
    }

    @RequestMapping("/addView")
    public String addView(Model model) {
        Permission permission = new Permission();
        permission.setPermType("PAGE");
        permission.setParentId(0);
        permission.setStatus(1);
        permission.setSortNo(0);
        model.addAttribute("permission", permission);
        model.addAttribute("formAction", "/permission/add");
        return "pages/permission-form";
    }

    @RequestMapping("/add")
    public String add(Permission permission, Model model, HttpServletRequest request) {
        try {
            permissionService.add(permission);
            operationLogService.record("permission", "add", "add permission: " + permission.getPermCode(), request);
            return "redirect:/permission/permissionList";
        } catch (RuntimeException e) {
            model.addAttribute("permission", permission);
            model.addAttribute("formAction", "/permission/add");
            model.addAttribute("errMsg", e.getMessage());
            return "pages/permission-form";
        }
    }

    @RequestMapping("/updateView")
    public String updateView(@RequestParam("id") Integer id, Model model) {
        model.addAttribute("permission", permissionService.getById(id));
        model.addAttribute("formAction", "/permission/update");
        return "pages/permission-form";
    }

    @RequestMapping("/update")
    public String update(Permission permission, Model model, HttpServletRequest request) {
        try {
            permissionService.update(permission);
            operationLogService.record("permission", "update", "update permission: " + permission.getPermCode(), request);
            return "redirect:/permission/permissionList";
        } catch (RuntimeException e) {
            model.addAttribute("permission", permission);
            model.addAttribute("formAction", "/permission/update");
            model.addAttribute("errMsg", e.getMessage());
            return "pages/permission-form";
        }
    }

    @RequestMapping("/del")
    public String del(@RequestParam("id") Integer id, HttpServletRequest request) {
        Permission permission = permissionService.getById(id);
        permissionService.delete(id);
        operationLogService.record("permission", "delete", "delete permission: " + permission.getPermCode(), request);
        return "redirect:/permission/permissionList";
    }
}
