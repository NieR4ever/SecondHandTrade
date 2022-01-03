package com.example.sht.service.impl;

import com.example.sht.domain.Category;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;
@RunWith(SpringRunner.class)
@SpringBootTest
class CategoryServiceImplTest {
    @Autowired
    CategoryServiceImpl service;
    @Test
    void findAll() {
        System.out.println(service.findAll());
    }

    @Test
    void findById() {
        System.out.println(service.findById(1));
    }

    @Test
    void saveCategory() {
        Category category = new Category();
        category.setCategory("运动用品");
        System.out.println(service.saveCategory(category));
    }

    @Test
    void updateCategory() {
        Category category = new Category();
        category.setId(1);
        category.setCategory("体育用品");
        service.updateCategory(category);
    }

    @Test
    void deleteCategory() {
        System.out.println(service.deleteCategory(1));
    }
}