package com.dtdx.controller;

import com.dtdx.entity.User;
import com.dtdx.service.OperationLogService;
import com.dtdx.service.RoleService;
import com.dtdx.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private OperationLogService operationLogService;

    @Autowired
    private RoleService roleService;

    @RequestMapping("/userList")
    public String userList(Model model, User user) {
        List<User> userList = userService.userList(user);
        model.addAttribute("userList", userList);
        return "pages/user-info";
    }

    @RequestMapping("/addView")
    public String addView(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("roleList", roleService.listAllActive());
        model.addAttribute("selectedRoleIds", null);
        model.addAttribute("formAction", "/user/add");
        return "pages/user-add";
    }

    @RequestMapping("/add")
    public String add(User user, HttpServletRequest request) {
        userService.add(user);
        roleService.bindUserRoles(user.getId(), user.getRoleIds());
        operationLogService.record("user", "add", "add user: " + user.getLoginName(), request);
        return "redirect:/user/userList";
    }

    @RequestMapping("/updateView")
    public String updateView(@RequestParam("id") Integer id, Model model) {
        User user = userService.getById(id);
        model.addAttribute("user", user);
        model.addAttribute("roleList", roleService.listAllActive());
        model.addAttribute("selectedRoleIds", userService.findRoleIdsByUserId(id));
        model.addAttribute("formAction", "/user/update");
        return "pages/user-add";
    }

    @RequestMapping("/update")
    public String update(User user, HttpServletRequest request) {
        userService.update(user);
        roleService.bindUserRoles(user.getId(), user.getRoleIds());
        operationLogService.record("user", "update", "update user: " + user.getLoginName(), request);
        return "redirect:/user/userList";
    }

    @RequestMapping("/del")
    public String del(@RequestParam("id") Integer id, HttpServletRequest request) {
        userService.delete(id);
        operationLogService.record("user", "delete", "delete user id: " + id, request);
        return "redirect:/user/userList";
    }
}
