package com.dtdx.service;

import com.dtdx.dao.UserDao;
import com.dtdx.dao.UserRoleDao;
import com.dtdx.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserRoleDao userRoleDao;

    public User Login(String loginName, String password) {
        if (loginName == null || "".equals(loginName.trim())) {
            throw new RuntimeException("账号不能为空");
        }
        if (password == null || "".equals(password.trim())) {
            throw new RuntimeException("密码不能为空");
        }
        User user = userDao.getUserByLoginName(loginName);
        if (user == null) {
            throw new RuntimeException("账号或密码错误");
        }
        if (!user.getPassword().equals(password)) {
            throw new RuntimeException("账号或密码错误");
        }
        if (user.getStatus() == null || user.getStatus() != 1) {
            throw new RuntimeException("账号已停用，请联系管理员");
        }
        return user;
    }

    public List<User> userList(User user) {
        return userDao.userList(user);
    }

    public User getById(Integer id) {
        return userDao.getById(id);
    }

    public void add(User user) {
        if (user.getStatus() == null) {
            user.setStatus(1);
        }
        userDao.add(user);
    }

    public void update(User user) {
        userDao.update(user);
    }

    public void delete(Integer id) {
        userRoleDao.deleteByUserId(id);
        userDao.delete(id);
    }

    public List<Integer> findRoleIdsByUserId(Integer userId) {
        return userRoleDao.findRoleIdsByUserId(userId);
    }
}
