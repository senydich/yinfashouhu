package com.dtdx.dao;

import com.dtdx.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class UserDaoTest {
    @Autowired
    private UserDao userDao;

    @Test
    public void userListTest(){
        List<User> userList = userDao.userList(new User());
        System.out.println(userList);
    }

    @Test
    public void getUserByLoginNameTest(){
        User user = userDao.getUserByLoginName("zhangsan1");
        System.out.println(user);
    }

}
