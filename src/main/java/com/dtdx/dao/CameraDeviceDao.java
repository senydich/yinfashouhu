package com.dtdx.dao;

import com.dtdx.entity.CameraDevice;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CameraDeviceDao {
    List<CameraDevice> cameraDeviceList(CameraDevice cameraDevice);

    CameraDevice getById(@Param("id") Integer id);

    CameraDevice getByDeviceIp(@Param("deviceIp") String deviceIp);

    void add(CameraDevice cameraDevice);

    void update(CameraDevice cameraDevice);

    void delete(@Param("id") Integer id);
}
