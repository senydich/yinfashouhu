package com.dtdx.service;

import com.dtdx.dao.CameraDeviceDao;
import com.dtdx.entity.CameraDevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CameraDeviceService {
    @Autowired
    private CameraDeviceDao cameraDeviceDao;

    public List<CameraDevice> cameraDeviceList(CameraDevice cameraDevice) {
        return cameraDeviceDao.cameraDeviceList(cameraDevice);
    }

    public CameraDevice getById(Integer id) {
        if (id == null) {
            throw new RuntimeException("摄像头ID不能为空");
        }
        CameraDevice cameraDevice = cameraDeviceDao.getById(id);
        if (cameraDevice == null) {
            throw new RuntimeException("摄像头设备不存在");
        }
        return cameraDevice;
    }

    public void add(CameraDevice cameraDevice) {
        prepareAndValidate(cameraDevice);
        checkDeviceIpUnique(cameraDevice);
        cameraDeviceDao.add(cameraDevice);
    }

    public void update(CameraDevice cameraDevice) {
        if (cameraDevice.getId() == null) {
            throw new RuntimeException("摄像头ID不能为空");
        }
        getById(cameraDevice.getId());
        prepareAndValidate(cameraDevice);
        checkDeviceIpUnique(cameraDevice);
        cameraDeviceDao.update(cameraDevice);
    }

    public void delete(Integer id) {
        getById(id);
        cameraDeviceDao.delete(id);
    }

    private void prepareAndValidate(CameraDevice cameraDevice) {
        if (cameraDevice == null) {
            throw new RuntimeException("摄像头设备不能为空");
        }
        cameraDevice.setDeviceName(trim(cameraDevice.getDeviceName()));
        cameraDevice.setDeviceLocation(trim(cameraDevice.getDeviceLocation()));
        cameraDevice.setDeviceIp(trim(cameraDevice.getDeviceIp()));
        cameraDevice.setStatus(trim(cameraDevice.getStatus()));
        cameraDevice.setRemark(trim(cameraDevice.getRemark()));

        if (isBlank(cameraDevice.getDeviceName())) {
            throw new RuntimeException("摄像头名称不能为空");
        }
        if (isBlank(cameraDevice.getDeviceLocation())) {
            throw new RuntimeException("安装位置不能为空");
        }
        if (isBlank(cameraDevice.getDeviceIp())) {
            throw new RuntimeException("IP地址不能为空");
        }
        if (isBlank(cameraDevice.getStatus())) {
            cameraDevice.setStatus("ONLINE");
        }
    }

    private void checkDeviceIpUnique(CameraDevice cameraDevice) {
        CameraDevice exists = cameraDeviceDao.getByDeviceIp(cameraDevice.getDeviceIp());
        if (exists != null && (cameraDevice.getId() == null || !cameraDevice.getId().equals(exists.getId()))) {
            throw new RuntimeException("IP地址已存在");
        }
    }

    private boolean isBlank(String value) {
        return value == null || "".equals(value.trim());
    }

    private String trim(String value) {
        return value == null ? null : value.trim();
    }
}
