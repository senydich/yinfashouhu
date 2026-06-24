package com.dtdx.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserRoleDao {
    List<Integer> findRoleIdsByUserId(@Param("userId") Integer userId);

    void deleteByUserId(@Param("userId") Integer userId);

    void deleteByRoleId(@Param("roleId") Integer roleId);

    void batchAddUserRoles(@Param("userId") Integer userId, @Param("roleIds") List<Integer> roleIds);
}
