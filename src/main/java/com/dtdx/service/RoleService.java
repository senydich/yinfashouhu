package com.dtdx.service;

import com.dtdx.dao.RoleDao;
import com.dtdx.dao.UserRoleDao;
import com.dtdx.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class RoleService {

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private UserRoleDao userRoleDao;

    public List<Role> roleList(Role role) {
        return roleDao.roleList(role);
    }

    public List<Role> listAllActive() {
        return roleDao.listAllActive();
    }

    public Role getById(Integer id) {
        if (id == null) {
            throw new RuntimeException("角色ID不能为空");
        }
        Role role = roleDao.getById(id);
        if (role == null) {
            throw new RuntimeException("角色不存在");
        }
        return role;
    }

    public List<Integer> getPermissionIdsByRoleId(Integer roleId) {
        return roleDao.getPermissionIdsByRoleId(roleId);
    }

    public List<String> getPermissionUrlsByUserId(Integer userId) {
        List<String> urls = roleDao.getPermissionUrlsByUserId(userId);
        return urls == null ? Collections.<String>emptyList() : urls;
    }

    public List<String> getRoleNamesByUserId(Integer userId) {
        List<String> names = roleDao.getRoleNamesByUserId(userId);
        return names == null ? Collections.<String>emptyList() : names;
    }

    public List<String> getRoleCodesByUserId(Integer userId) {
        List<String> codes = roleDao.getRoleCodesByUserId(userId);
        return codes == null ? Collections.<String>emptyList() : codes;
    }

    public void add(Role role) {
        validate(role);
        Role exist = roleDao.getByRoleCode(role.getRoleCode());
        if (exist != null) {
            throw new RuntimeException("角色编码已存在");
        }
        roleDao.add(role);
        savePermissions(role.getId(), role.getPermissionIds());
    }

    public void update(Role role) {
        if (role.getId() == null) {
            throw new RuntimeException("角色ID不能为空");
        }
        getById(role.getId());
        validate(role);
        Role exist = roleDao.getByRoleCode(role.getRoleCode());
        if (exist != null && !exist.getId().equals(role.getId())) {
            throw new RuntimeException("角色编码已存在");
        }
        roleDao.update(role);
        savePermissions(role.getId(), role.getPermissionIds());
    }

    public void delete(Integer id) {
        getById(id);
        roleDao.deleteRolePermissions(id);
        userRoleDao.deleteByRoleId(id);
        roleDao.delete(id);
    }

    public void savePermissions(Integer roleId, List<Integer> permissionIds) {
        roleDao.deleteRolePermissions(roleId);
        if (permissionIds != null && !permissionIds.isEmpty()) {
            roleDao.batchAddRolePermissions(roleId, permissionIds);
        }
    }

    public void bindUserRoles(Integer userId, List<Integer> roleIds) {
        userRoleDao.deleteByUserId(userId);
        if (roleIds != null && !roleIds.isEmpty()) {
            userRoleDao.batchAddUserRoles(userId, roleIds);
        }
    }

    private void validate(Role role) {
        if (role == null) {
            throw new RuntimeException("角色信息不能为空");
        }
        role.setRoleCode(trim(role.getRoleCode()));
        role.setRoleName(trim(role.getRoleName()));
        role.setRemark(trim(role.getRemark()));
        if (isBlank(role.getRoleCode())) {
            throw new RuntimeException("角色编码不能为空");
        }
        if (isBlank(role.getRoleName())) {
            throw new RuntimeException("角色名称不能为空");
        }
        if (role.getSortNo() == null) {
            role.setSortNo(0);
        }
        if (role.getStatus() == null) {
            role.setStatus(1);
        }
    }

    private boolean isBlank(String value) {
        return value == null || "".equals(value.trim());
    }

    private String trim(String value) {
        return value == null ? null : value.trim();
    }
}
