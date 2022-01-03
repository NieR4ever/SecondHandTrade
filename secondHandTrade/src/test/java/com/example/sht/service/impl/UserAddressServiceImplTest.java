package com.example.sht.service.impl;

import com.example.sht.domain.UserAddress;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;
@RunWith(SpringRunner.class)
@SpringBootTest
class UserAddressServiceImplTest {

    @Autowired
    UserAddressServiceImpl userAddressService;
    @Autowired
    UserServiceImpl userService;
    @Test
    void findAll() {
        System.out.println(userAddressService.findAll());
    }

    @Test
    void findById() {
        System.out.println(userAddressService.findById(2));
    }

    @Test
    void saveUserAddress() {
        UserAddress userAddress = new UserAddress();
        userAddress.setUserId(2);
        userAddress.setAddress("云浮");
        System.out.println(userAddressService.saveUserAddress(userAddress));
    }

    @Test
    void updateUserAddress() {
        UserAddress userAddress = new UserAddress();
        userAddress.setId(1);
        userAddress.setAddress("广州");
    }

    @Test
    void deleteUserAddress() {
    }
}