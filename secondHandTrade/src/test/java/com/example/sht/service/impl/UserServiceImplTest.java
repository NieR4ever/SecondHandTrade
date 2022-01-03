package com.example.sht.service.impl;

import com.example.sht.SecondHandTradeApplication;
import com.example.sht.domain.User;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@RunWith(SpringRunner.class)
@SpringBootTest
class UserServiceImplTest {

    @Autowired
    UserServiceImpl userService;


//    @Test
//    void findAll() {
//        List<User> users = userService.findAll();
//        System.out.println(users);
//    }
//
//    @Test
//    void findById() {
//        User user = userService.findById(2);
//        System.out.println(user);
//    }
//
//    @Test
//    void saveUser() {
//        User user = new User();
//        user.setName("张三");
//        user.setPassword("111111");
//
//        int i = userService.saveUser(user);
//        System.out.println(i);
//    }
//
//    @Test
//    void updateUser() {
//        User user = new User();
//        user.setId(2);
//        user.setName("李四");
//        user.setPassword("222222");
//        int i = userService.updateUser(user);
//        System.out.println(i);
//    }
//
//    @Test
//    void deleteUser() {
//        int i = userService.deleteUser(3);
//        System.out.println(i);
//    }
}