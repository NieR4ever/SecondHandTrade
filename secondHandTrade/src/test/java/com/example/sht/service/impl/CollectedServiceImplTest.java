package com.example.sht.service.impl;

import com.example.sht.domain.Collected;
import com.example.sht.domain.Post;
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
class CollectedServiceImplTest {
    @Autowired
    CollectedServiceImpl collectedService;
    @Autowired
    PostServiceImpl postService;
    @Autowired
    UserServiceImpl userService;
    @Test
    void findAll() {
        System.out.println(collectedService.findAll());
    }

    @Test
    void findById() {
        System.out.println(collectedService.findById(1));
    }

    @Test
    void saveCollected() {
        Collected collected = new Collected();
        collected.setPost(postService.findById(2));
        collected.setUser(userService.findById(2));
        System.out.println(collectedService.saveCollected(collected));
    }

    @Test
    void updateCollected() {
        Collected collected = new Collected();
        collected.setId(1);
        collected.setUser(userService.findById(4));
        collected.setPost(new Post());
        System.out.println(collectedService.updateCollected(collected));
    }

    @Test
    void deleteCollected() {
    }

    @Test
    void findByUserId() {
        User user = userService.findById(4);
        List<Collected> collectedList = collectedService.findByUserId(user.getId());
        System.out.println(collectedList);
    }
}