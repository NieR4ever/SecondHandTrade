package com.example.sht.dao;

import com.example.sht.domain.UserAddress;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserAddressDao {
    List<UserAddress> findAll();
    List<UserAddress> findByUserId(long userId);
    UserAddress findById(long id);
    int saveUserAddress(UserAddress userAddress);
    int updateUserAddress(UserAddress userAddress);
    int deleteUserAddress(long id);
}
