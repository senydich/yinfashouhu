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
import javax.servlet.http.HttpSession;

@Controller
public class LoginController {
    @Autowired
    private UserService userService;

    @Autowired
    private OperationLogService operationLogService;

    @Autowired
    private RoleService roleService;

    @RequestMapping("/")
    public String loginView(@RequestParam(value = "errMsg", required = false) String errMsg, Model model) {
        if (errMsg != null && !"".equals(errMsg.trim())) {
            model.addAttribute("errMsg", errMsg);
        }
        return "login";
    }

    @RequestMapping("/login")
    public String login(@RequestParam("loginName") String loginName,
                        @RequestParam("password") String password,
                        Model model,
                        HttpServletRequest request) {
        try {
            User user = userService.Login(loginName, password);
            request.getSession().setAttribute("loginUser", user);
            request.getSession().setAttribute("loginName", user.getLoginName());
            request.getSession().setAttribute("permissionUrls", roleService.getPermissionUrlsByUserId(user.getId()));
            request.getSession().setAttribute("roleNames", roleService.getRoleNamesByUserId(user.getId()));
            request.getSession().setAttribute("roleCodes", roleService.getRoleCodesByUserId(user.getId()));
            operationLogService.record(user.getLoginName(), "system login", "login", "login success", 1, request);
            return "redirect:/main";
        } catch (Exception e) {
            operationLogService.record(loginName, "system login", "login", "login failed", 0, request);
            model.addAttribute("errMsg", e.getMessage() == null || "".equals(e.getMessage().trim())
                    ? "账号或密码错误"
                    : e.getMessage());
            return "login";
        }
    }

    @RequestMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        String loginName = session == null ? "unknown" : (String) session.getAttribute("loginName");
        operationLogService.record(loginName, "system logout", "logout", "logout success", 1, request);
        if (session != null) {
            session.invalidate();
        }
        return "redirect:/";
    }
}
