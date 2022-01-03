package com.example.sht.dao;

import com.example.sht.domain.User;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@RunWith(SpringRunner.class)
@SpringBootTest
class UserDaoTest {
    @Autowired
    UserDao dao;

    @Test
    void findAll() {
        List<User> users = dao.findAll();
        System.out.println(users);
    }

    @Test
    void findById() {
        User user = dao.findById(1);
        System.out.println(user);
    }

    @Test
    void saveUser() {
        User user = new User();
        user.setName("张三");
        user.setPassword("111111");

        int i = dao.saveUser(user);
        System.out.println(i);
    }

    @Test
    void updateUser() {
        User user = new User();
        user.setId(1);
        user.setName("张三");
        user.setPassword("222222");
        int i = dao.updateUser(user);
        System.out.println(i);
        
    }

    @Test
    void deleteUser() {
        int i = dao.deleteUser(1);
        System.out.println(i);
    }
}