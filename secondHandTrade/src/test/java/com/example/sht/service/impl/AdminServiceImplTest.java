package com.example.sht.service.impl;

import com.example.sht.domain.Admin;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class AdminServiceImplTest {

    @Autowired
    AdminServiceImpl service;

    @Test
    void findAll() {
        System.out.println(service.findAll());
    }

    @Test
    void findById() {
        System.out.println(service.findById(3L));
    }

    @Test
    void saveAdmin() {
        Admin admin = new Admin();
        admin.setName("张三");
        admin.setPassword("111111");
        System.out.println(service.saveAdmin(admin));
    }

    @Test
    void updateAdmin() {
        Admin admin = new Admin();
        admin.setId(3L);
        admin.setName("张三");
        admin.setPassword("333333");
        System.out.println(service.saveAdmin(admin));
    }

    @Test
    void deleteAdmin() {
        System.out.println(service.deleteAdmin(3L));
    }
}