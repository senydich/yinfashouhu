package com.dtdx.interceptor;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dtdx.entity.User;
import com.dtdx.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class AuthInterceptor implements HandlerInterceptor {

    @Autowired
    private UserService userService;

    private static final Set<String> PUBLIC_URIS = new HashSet<String>();
    private static final Set<String> AUTHENTICATED_URIS = new HashSet<String>();

    static {
        PUBLIC_URIS.add("/");
        PUBLIC_URIS.add("/login");

        AUTHENTICATED_URIS.add("/logout");
        AUTHENTICATED_URIS.add("/main");
        AUTHENTICATED_URIS.add("/top");
        AUTHENTICATED_URIS.add("/left");
        AUTHENTICATED_URIS.add("/dashboard");
        AUTHENTICATED_URIS.add("/noPermission");
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String uri = normalizeUri(request);
        if (isPublic(uri)) {
            return true;
        }

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("loginUser") == null) {
            response.sendRedirect(request.getContextPath() + "/");
            return false;
        }

        Object loginUserObj = session.getAttribute("loginUser");
        if (!(loginUserObj instanceof User)) {
            session.invalidate();
            response.sendRedirect(request.getContextPath() + "/");
            return false;
        }

        User currentUser = (User) loginUserObj;
        User dbUser = userService.getById(currentUser.getId());
        if (dbUser == null || dbUser.getStatus() == null || dbUser.getStatus() != 1) {
            session.invalidate();
            response.sendRedirect(request.getContextPath() + "/?errMsg=%E8%B4%A6%E5%8F%B7%E5%B7%B2%E5%81%9C%E7%94%A8%EF%BC%8C%E8%AF%B7%E8%81%94%E7%B3%BB%E7%AE%A1%E7%90%86%E5%91%98");
            return false;
        }
        if (isSuperAdmin(session, currentUser)) {
            return true;
        }

        if (isAuthenticatedOnly(uri)) {
            return true;
        }

        Object permissionUrlsObj = session.getAttribute("permissionUrls");
        if (!(permissionUrlsObj instanceof Collection)) {
            response.sendRedirect(request.getContextPath() + "/noPermission");
            return false;
        }

        Collection<?> permissionUrls = (Collection<?>) permissionUrlsObj;
        if (permissionUrls.isEmpty()) {
            response.sendRedirect(request.getContextPath() + "/noPermission");
            return false;
        }

        for (Object permissionUrl : permissionUrls) {
            if (isMatched(uri, permissionUrl == null ? null : String.valueOf(permissionUrl))) {
                return true;
            }
        }

        response.sendRedirect(request.getContextPath() + "/noPermission");
        return false;
    }

    private boolean isSuperAdmin(HttpSession session, User user) {
        if (user != null && "admin".equalsIgnoreCase(user.getLoginName())) {
            return true;
        }

        Object roleCodesObj = session.getAttribute("roleCodes");
        if (!(roleCodesObj instanceof Collection)) {
            return false;
        }

        Collection<?> roleCodes = (Collection<?>) roleCodesObj;
        for (Object roleCode : roleCodes) {
            if ("admin".equalsIgnoreCase(String.valueOf(roleCode))) {
                return true;
            }
        }
        return false;
    }

    private String normalizeUri(HttpServletRequest request) {
        String uri = request.getRequestURI();
        String contextPath = request.getContextPath();
        if (contextPath != null && !"".equals(contextPath) && uri.startsWith(contextPath)) {
            uri = uri.substring(contextPath.length());
        }
        if (uri == null || "".equals(uri)) {
            return "/";
        }
        return uri;
    }

    private boolean isPublic(String uri) {
        if (PUBLIC_URIS.contains(uri)) {
            return true;
        }
        return uri.startsWith("/css/")
                || uri.startsWith("/js/")
                || uri.startsWith("/images/")
                || uri.startsWith("/webjars/")
                || uri.startsWith("/error")
                || "/favicon.ico".equals(uri);
    }

    private boolean isAuthenticatedOnly(String uri) {
        return AUTHENTICATED_URIS.contains(uri);
    }

    private boolean isMatched(String uri, String permissionUrl) {
        if (permissionUrl == null) {
            return false;
        }
        String normalizedPermissionUrl = permissionUrl.trim();
        if ("".equals(normalizedPermissionUrl)) {
            return false;
        }
        if (uri.equals(normalizedPermissionUrl)) {
            return true;
        }
        if (normalizedPermissionUrl.endsWith("/**")) {
            String prefix = normalizedPermissionUrl.substring(0, normalizedPermissionUrl.length() - 3);
            return uri.equals(prefix) || uri.startsWith(prefix + "/");
        }
        if (normalizedPermissionUrl.endsWith("/*")) {
            String prefix = normalizedPermissionUrl.substring(0, normalizedPermissionUrl.length() - 2);
            if (!uri.startsWith(prefix + "/")) {
                return false;
            }
            return uri.indexOf('/', prefix.length() + 1) < 0;
        }
        return false;
    }
}
