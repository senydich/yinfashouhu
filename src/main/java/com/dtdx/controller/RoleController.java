package com.dtdx.controller;

import com.dtdx.entity.Permission;
import com.dtdx.entity.Role;
import com.dtdx.service.OperationLogService;
import com.dtdx.service.PermissionService;
import com.dtdx.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private PermissionService permissionService;

    @Autowired
    private OperationLogService operationLogService;

    @RequestMapping("/roleList")
    public String roleList(Model model, Role role) {
        model.addAttribute("roleList", roleService.roleList(role));
        return "pages/role-info";
    }

    @RequestMapping("/addView")
    public String addView(Model model) {
        Role role = new Role();
        role.setStatus(1);
        role.setSortNo(0);
        model.addAttribute("role", role);
        model.addAttribute("permissionList", permissionService.listAllActive());
        model.addAttribute("selectedPermissionIds", null);
        model.addAttribute("formAction", "/role/add");
        return "pages/role-form";
    }

    @RequestMapping("/add")
    public String add(Role role, Model model, HttpServletRequest request) {
        try {
            roleService.add(role);
            operationLogService.record("role", "add", "add role: " + role.getRoleCode(), request);
            return "redirect:/role/roleList";
        } catch (RuntimeException e) {
            model.addAttribute("role", role);
            model.addAttribute("permissionList", permissionService.listAllActive());
            model.addAttribute("selectedPermissionIds", role.getPermissionIds());
            model.addAttribute("formAction", "/role/add");
            model.addAttribute("errMsg", e.getMessage());
            return "pages/role-form";
        }
    }

    @RequestMapping("/updateView")
    public String updateView(@RequestParam("id") Integer id, Model model) {
        Role role = roleService.getById(id);
        List<Integer> selectedPermissionIds = roleService.getPermissionIdsByRoleId(id);
        model.addAttribute("role", role);
        model.addAttribute("permissionList", permissionService.listAllActive());
        model.addAttribute("selectedPermissionIds", selectedPermissionIds);
        model.addAttribute("formAction", "/role/update");
        return "pages/role-form";
    }

    @RequestMapping("/update")
    public String update(Role role, Model model, HttpServletRequest request) {
        try {
            roleService.update(role);
            operationLogService.record("role", "update", "update role: " + role.getRoleCode(), request);
            return "redirect:/role/roleList";
        } catch (RuntimeException e) {
            model.addAttribute("role", role);
            model.addAttribute("permissionList", permissionService.listAllActive());
            model.addAttribute("selectedPermissionIds", role.getPermissionIds());
            model.addAttribute("formAction", "/role/update");
            model.addAttribute("errMsg", e.getMessage());
            return "pages/role-form";
        }
    }

    @RequestMapping("/del")
    public String del(@RequestParam("id") Integer id, HttpServletRequest request) {
        Role role = roleService.getById(id);
        roleService.delete(id);
        operationLogService.record("role", "delete", "delete role: " + role.getRoleCode(), request);
        return "redirect:/role/roleList";
    }
}
