package com.example.sht.service;

import com.example.sht.dao.UserDao;
import com.example.sht.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

public interface UserService {
    List<User> findAll();
    User findById(long id);
    int saveUser(User user);
    int updateUser(User user);
    int deleteUser(long id);
    User findUserForLogin(User user);
    User findByName(User user);

}
