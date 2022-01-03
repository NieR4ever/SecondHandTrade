package com.example.sht.service;

import com.example.sht.domain.User;
import com.example.sht.domain.UserAddress;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserAddressService {
    List<UserAddress> findAll();
    List<UserAddress> findById(long userId);
    int saveUserAddress(UserAddress userAddress);
    int updateUserAddress(UserAddress userAddress);
    int deleteUserAddress(long id);
}
