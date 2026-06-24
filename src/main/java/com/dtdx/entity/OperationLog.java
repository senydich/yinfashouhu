package com.dtdx.entity;

import java.util.Date;

public class OperationLog {
    private Integer id;
    private String operatorName;
    private String moduleName;
    private String operationType;
    private String operationContent;
    private String ipAddress;
    private Integer status;
    private Date createDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public String getOperationType() {
        return operationType;
    }

    public void setOperationType(String operationType) {
        this.operationType = operationType;
    }

    public String getOperationContent() {
        return operationContent;
    }

    public void setOperationContent(String operationContent) {
        this.operationContent = operationContent;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Override
    public String toString() {
        return "OperationLog{" +
                "id=" + id +
                ", operatorName='" + operatorName + '\'' +
                ", moduleName='" + moduleName + '\'' +
                ", operationType='" + operationType + '\'' +
                ", operationContent='" + operationContent + '\'' +
                ", ipAddress='" + ipAddress + '\'' +
                ", status=" + status +
                ", createDate=" + createDate +
                '}';
    }
}
