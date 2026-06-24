package com.dtdx.service;

import com.dtdx.dao.EdgeGatewayDao;
import com.dtdx.entity.EdgeGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EdgeGatewayService {
    @Autowired
    private EdgeGatewayDao edgeGatewayDao;

    public List<EdgeGateway> edgeGatewayList(EdgeGateway edgeGateway) {
        return edgeGatewayDao.edgeGatewayList(edgeGateway);
    }

    public EdgeGateway getById(Integer id) {
        if (id == null) {
            throw new RuntimeException("网关ID不能为空");
        }
        EdgeGateway edgeGateway = edgeGatewayDao.getById(id);
        if (edgeGateway == null) {
            throw new RuntimeException("边缘网关不存在");
        }
        return edgeGateway;
    }

    public void add(EdgeGateway edgeGateway) {
        prepareAndValidate(edgeGateway);
        checkGatewayCodeUnique(edgeGateway);
        edgeGatewayDao.add(edgeGateway);
    }

    public void update(EdgeGateway edgeGateway) {
        if (edgeGateway.getId() == null) {
            throw new RuntimeException("网关ID不能为空");
        }
        getById(edgeGateway.getId());
        prepareAndValidate(edgeGateway);
        checkGatewayCodeUnique(edgeGateway);
        edgeGatewayDao.update(edgeGateway);
    }

    public void delete(Integer id) {
        getById(id);
        edgeGatewayDao.delete(id);
    }

    private void prepareAndValidate(EdgeGateway edgeGateway) {
        if (edgeGateway == null) {
            throw new RuntimeException("边缘网关不能为空");
        }
        edgeGateway.setGatewayCode(trim(edgeGateway.getGatewayCode()));
        edgeGateway.setGatewayName(trim(edgeGateway.getGatewayName()));
        edgeGateway.setInstallLocation(trim(edgeGateway.getInstallLocation()));
        edgeGateway.setGatewayIp(trim(edgeGateway.getGatewayIp()));
        edgeGateway.setMacAddress(trim(edgeGateway.getMacAddress()));
        edgeGateway.setAccessProtocol(trim(edgeGateway.getAccessProtocol()));
        edgeGateway.setFirmwareVersion(trim(edgeGateway.getFirmwareVersion()));
        edgeGateway.setStatus(trim(edgeGateway.getStatus()));
        edgeGateway.setRemark(trim(edgeGateway.getRemark()));

        if (isBlank(edgeGateway.getGatewayCode())) {
            throw new RuntimeException("网关编号不能为空");
        }
        if (isBlank(edgeGateway.getGatewayName())) {
            throw new RuntimeException("网关名称不能为空");
        }
        if (isBlank(edgeGateway.getInstallLocation())) {
            throw new RuntimeException("安装位置不能为空");
        }
        if (edgeGateway.getManagedDeviceCount() == null) {
            edgeGateway.setManagedDeviceCount(0);
        }
        if (edgeGateway.getManagedDeviceCount() < 0) {
            throw new RuntimeException("管理设备数不能小于0");
        }
        if (isBlank(edgeGateway.getAccessProtocol())) {
            edgeGateway.setAccessProtocol("MQTT");
        }
        if (isBlank(edgeGateway.getStatus())) {
            edgeGateway.setStatus("ONLINE");
        }
    }

    private void checkGatewayCodeUnique(EdgeGateway edgeGateway) {
        EdgeGateway exists = edgeGatewayDao.getByGatewayCode(edgeGateway.getGatewayCode());
        if (exists != null && (edgeGateway.getId() == null || !edgeGateway.getId().equals(exists.getId()))) {
            throw new RuntimeException("网关编号已存在");
        }
    }

    private boolean isBlank(String value) {
        return value == null || "".equals(value.trim());
    }

    private String trim(String value) {
        return value == null ? null : value.trim();
    }
}
