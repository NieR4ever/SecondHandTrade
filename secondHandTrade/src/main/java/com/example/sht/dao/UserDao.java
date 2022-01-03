package com.example.sht.dao;

import com.example.sht.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
public interface UserDao {
    List<User> findAll();
    User findById(long id);
    int saveUser(User user);
    int updateUser(User user);
    int deleteUser(long id);
    User findByName(User user);
    User findUserForLogin(User user);

}
