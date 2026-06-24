package com.dtdx.service;

import com.dtdx.dao.PermissionDao;
import com.dtdx.dao.RoleDao;
import com.dtdx.entity.Permission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionService {

    @Autowired
    private PermissionDao permissionDao;

    @Autowired
    private RoleDao roleDao;

    public List<Permission> permissionList(Permission permission) {
        return permissionDao.permissionList(permission);
    }

    public List<Permission> listAllActive() {
        return permissionDao.listAllActive();
    }

    public Permission getById(Integer id) {
        if (id == null) {
            throw new RuntimeException("权限ID不能为空");
        }
        Permission permission = permissionDao.getById(id);
        if (permission == null) {
            throw new RuntimeException("权限不存在");
        }
        return permission;
    }

    public void add(Permission permission) {
        validate(permission);
        if (permissionDao.getByPermCode(permission.getPermCode()) != null) {
            throw new RuntimeException("权限编码已存在");
        }
        if (permissionDao.getByPermUrl(permission.getPermUrl()) != null) {
            throw new RuntimeException("权限URL已存在");
        }
        permissionDao.add(permission);
    }

    public void update(Permission permission) {
        if (permission.getId() == null) {
            throw new RuntimeException("权限ID不能为空");
        }
        getById(permission.getId());
        validate(permission);
        Permission codeExist = permissionDao.getByPermCode(permission.getPermCode());
        if (codeExist != null && !codeExist.getId().equals(permission.getId())) {
            throw new RuntimeException("权限编码已存在");
        }
        Permission urlExist = permissionDao.getByPermUrl(permission.getPermUrl());
        if (urlExist != null && !urlExist.getId().equals(permission.getId())) {
            throw new RuntimeException("权限URL已存在");
        }
        permissionDao.update(permission);
    }

    public void delete(Integer id) {
        getById(id);
        roleDao.deleteRolePermissionsByPermissionId(id);
        permissionDao.delete(id);
    }

    private void validate(Permission permission) {
        if (permission == null) {
            throw new RuntimeException("权限信息不能为空");
        }
        permission.setPermCode(trim(permission.getPermCode()));
        permission.setPermName(trim(permission.getPermName()));
        permission.setPermUrl(trim(permission.getPermUrl()));
        permission.setPermType(trim(permission.getPermType()));
        permission.setRemark(trim(permission.getRemark()));
        permission.setIcon(trim(permission.getIcon()));
        if (isBlank(permission.getPermCode())) {
            throw new RuntimeException("权限编码不能为空");
        }
        if (isBlank(permission.getPermName())) {
            throw new RuntimeException("权限名称不能为空");
        }
        if (isBlank(permission.getPermUrl())) {
            throw new RuntimeException("权限URL不能为空");
        }
        if (isBlank(permission.getPermType())) {
            permission.setPermType("PAGE");
        }
        if (permission.getParentId() == null) {
            permission.setParentId(0);
        }
        if (permission.getSortNo() == null) {
            permission.setSortNo(0);
        }
        if (permission.getStatus() == null) {
            permission.setStatus(1);
        }
    }

    private boolean isBlank(String value) {
        return value == null || "".equals(value.trim());
    }

    private String trim(String value) {
        return value == null ? null : value.trim();
    }
}
