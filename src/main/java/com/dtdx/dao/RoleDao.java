package com.dtdx.dao;

import com.dtdx.entity.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface RoleDao {
    List<Role> roleList(Role role);

    Role getById(@Param("id") Integer id);

    Role getByRoleCode(@Param("roleCode") String roleCode);

    void add(Role role);

    void update(Role role);

    void delete(@Param("id") Integer id);

    List<Role> listAllActive();

    List<Integer> getPermissionIdsByRoleId(@Param("roleId") Integer roleId);

    void deleteRolePermissions(@Param("roleId") Integer roleId);

    void deleteRolePermissionsByPermissionId(@Param("permissionId") Integer permissionId);

    void batchAddRolePermissions(@Param("roleId") Integer roleId, @Param("permissionIds") List<Integer> permissionIds);

    List<String> getPermissionUrlsByUserId(@Param("userId") Integer userId);

    List<String> getRoleNamesByUserId(@Param("userId") Integer userId);

    List<String> getRoleCodesByUserId(@Param("userId") Integer userId);
}
