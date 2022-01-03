package com.example.sht.service.impl;

import com.example.sht.domain.Category;
import com.example.sht.domain.Post;
import com.example.sht.domain.Quality;
import com.example.sht.domain.User;
import com.example.sht.service.CategoryService;
import com.example.sht.service.PostService;
import com.example.sht.service.QualityService;
import com.example.sht.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;
@RunWith(SpringRunner.class)
@SpringBootTest
class PostServiceImplTest {

    @Autowired
    PostService postService;
    @Autowired
    UserService userService;
    @Autowired
    CategoryService categoryService;
    @Autowired
    QualityService qualityService;
    @Test
    void findAll() {
//        System.out.println(postService.findAll());
    }

    @Test
    void findById() {
        System.out.println(postService.findById(2));
    }

    @Test
    void savePost() {
        Post post = new Post();
        post.setUser(userService.findById(2));
        post.setCategory(categoryService.findById(2));
        post.setTitle("你好");
        post.setContent("世界");
        post.setPrice(333.3);
        post.setCoverPictureUrl("https://cdn.jsdelivr.net/gh/NieR4ever/picture@main/wallpaper/wallhaven-8oxeyy.png");
        post.setQuality(qualityService.findById(1));
        postService.savePost(post);
    }

    @Test
    void updatePost() {
        Post post = new Post();
        post.setUser(new User());
        post.setQuality(new Quality());
        post.setCategory(new Category());
        post.setId(2);
        post.setContent("广药");
        postService.updatePost(post);
    }

    @Test
    void deletePost() {
    }
}