package com.dtdx.service;

import com.dtdx.dao.OperationLogDao;
import com.dtdx.entity.OperationLog;
import com.dtdx.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class OperationLogService {

    @Autowired
    private OperationLogDao operationLogDao;

    public List<OperationLog> operationLogList(OperationLog operationLog) {
        return operationLogDao.operationLogList(operationLog);
    }

    public OperationLog getById(Integer id) {
        return operationLogDao.getById(id);
    }

    public void add(OperationLog operationLog) {
        if (operationLog.getStatus() == null) {
            operationLog.setStatus(1);
        }
        operationLogDao.add(operationLog);
    }

    public void record(String moduleName, String operationType, String operationContent, HttpServletRequest request) {
        record(moduleName, operationType, operationContent, 1, request);
    }

    public void record(String moduleName, String operationType, String operationContent, Integer status, HttpServletRequest request) {
        OperationLog operationLog = new OperationLog();
        operationLog.setOperatorName(getOperatorName(request));
        operationLog.setModuleName(moduleName);
        operationLog.setOperationType(operationType);
        operationLog.setOperationContent(operationContent);
        operationLog.setIpAddress(getIpAddress(request));
        operationLog.setStatus(status);
        operationLogDao.add(operationLog);
    }

    public void record(String operatorName, String moduleName, String operationType, String operationContent, Integer status, HttpServletRequest request) {
        OperationLog operationLog = new OperationLog();
        if (operatorName == null || "".equals(operatorName.trim())) {
            operatorName = getOperatorName(request);
        }
        if (operatorName == null || "".equals(operatorName.trim())) {
            operatorName = "unknown";
        }
        operationLog.setOperatorName(operatorName);
        operationLog.setModuleName(moduleName);
        operationLog.setOperationType(operationType);
        operationLog.setOperationContent(operationContent);
        operationLog.setIpAddress(getIpAddress(request));
        operationLog.setStatus(status);
        operationLogDao.add(operationLog);
    }

    public void update(OperationLog operationLog) {
        operationLogDao.update(operationLog);
    }

    public void delete(Integer id) {
        operationLogDao.delete(id);
    }

    private String getOperatorName(HttpServletRequest request) {
        if (request != null && request.getSession(false) != null) {
            Object loginName = request.getSession(false).getAttribute("loginName");
            if (loginName != null && !"".equals(loginName.toString())) {
                return loginName.toString();
            }
            Object loginUser = request.getSession(false).getAttribute("loginUser");
            if (loginUser instanceof User) {
                User user = (User) loginUser;
                if (user.getLoginName() != null && !"".equals(user.getLoginName())) {
                    return user.getLoginName();
                }
                if (user.getRealName() != null && !"".equals(user.getRealName())) {
                    return user.getRealName();
                }
            }
        }
        return "unknown";
    }

    private String getIpAddress(HttpServletRequest request) {
        if (request == null) {
            return "";
        }
        String ip = request.getHeader("X-Forwarded-For");
        if (ip != null && !"".equals(ip) && !"unknown".equalsIgnoreCase(ip)) {
            return normalizeIp(ip.split(",")[0]);
        }
        ip = request.getHeader("X-Real-IP");
        if (ip != null && !"".equals(ip) && !"unknown".equalsIgnoreCase(ip)) {
            return normalizeIp(ip);
        }
        ip = request.getHeader("Proxy-Client-IP");
        if (ip != null && !"".equals(ip) && !"unknown".equalsIgnoreCase(ip)) {
            return normalizeIp(ip);
        }
        ip = request.getHeader("WL-Proxy-Client-IP");
        if (ip != null && !"".equals(ip) && !"unknown".equalsIgnoreCase(ip)) {
            return normalizeIp(ip);
        }
        return normalizeIp(request.getRemoteAddr());
    }

    private String normalizeIp(String ip) {
        if (ip == null) {
            return "";
        }
        ip = ip.trim();
        if ("0:0:0:0:0:0:0:1".equals(ip) || "::1".equals(ip)) {
            return "127.0.0.1";
        }
        return ip;
    }
}
