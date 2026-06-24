package com.dtdx.dao;

import com.dtdx.entity.Permission;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PermissionDao {
    List<Permission> permissionList(Permission permission);

    Permission getById(@Param("id") Integer id);

    Permission getByPermCode(@Param("permCode") String permCode);

    Permission getByPermUrl(@Param("permUrl") String permUrl);

    void add(Permission permission);

    void update(Permission permission);

    void delete(@Param("id") Integer id);

    List<Permission> listAllActive();
}
