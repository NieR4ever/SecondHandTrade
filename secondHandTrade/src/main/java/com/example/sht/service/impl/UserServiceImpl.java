package com.example.sht.service.impl;

import com.example.sht.dao.UserDao;
import com.example.sht.domain.Post;
import com.example.sht.domain.User;
import com.example.sht.service.UserService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Value("${upload.server.host}${upload.server.port}")
    public String pictureServer;
    @Autowired
    private UserDao userDao;
    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }

    @Override
    public User findById(long id) {
        User user = userDao.findById(id);
        return setPictureUrl(user);
    }

    @Override
    public int saveUser(User user) {
        int i = userDao.saveUser(user);
        return i;
    }

    @Override
    public int updateUser(User user) {
        int i = userDao.updateUser(user);
        return i;
    }

    @Override
    public int deleteUser(long id) {
        int i = userDao.deleteUser(id);

        return i;
    }

    @Override
    public User findUserForLogin(User user) {
        User user1 = userDao.findUserForLogin(user);
        return setPictureUrl(user1);
    }

    @Override
    public User findByName(User user) {
        return userDao.findByName(user);
    }

    private List<User> setPictureUrl(List<User> users) {
        for (User user : users) {
            String url = user.getAvatarUrl();
            String[] split = url.split("\\\\");
            user.setAvatarUrl(pictureServer+"/"+split[split.length-1]);
        }
        return users;
    }
    private User setPictureUrl(User user) {
        if (user ==null) {
            return user;
        }
        String url = user.getAvatarUrl();
        if (url != null) {
            String[] split = url.split("\\\\");
            user.setAvatarUrl(pictureServer + "/" + split[split.length - 1]);
        }
        return user;
    }
}
