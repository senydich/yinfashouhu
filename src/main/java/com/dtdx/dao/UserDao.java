package com.dtdx.dao;

import com.dtdx.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserDao {
    List<User> userList(User user);

    User getUserByLoginName(@Param("loginName") String loginName);

    User getById(@Param("id") Integer id);

    void add(User user);

    void update(User user);

    void delete(@Param("id") Integer id);
}
