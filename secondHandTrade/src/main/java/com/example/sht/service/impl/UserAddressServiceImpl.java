package com.example.sht.service.impl;

import com.example.sht.dao.UserAddressDao;
import com.example.sht.domain.UserAddress;
import com.example.sht.service.UserAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserAddressServiceImpl implements UserAddressService {

    @Autowired
    private UserAddressDao userAddressDao;
    @Override
    public List<UserAddress> findAll() {
        return userAddressDao.findAll();
    }

    @Override
    public List<UserAddress> findById(long userId) {
        return userAddressDao.findByUserId(userId);
    }

    @Override
    public int saveUserAddress(UserAddress userAddress) {
        return userAddressDao.saveUserAddress(userAddress);
    }

    @Override
    public int updateUserAddress(UserAddress userAddress) {
        return userAddressDao.updateUserAddress(userAddress);
    }

    @Override
    public int deleteUserAddress(long id) {
        return userAddressDao.deleteUserAddress(id);
    }
}
