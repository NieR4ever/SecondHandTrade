package com.example.sht.service;

import com.example.sht.domain.Category;
import com.example.sht.domain.Collected;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CategoryService {
    List<Category> findAll();
    List<Category> findByName(String category);
    Category findById(long id);
    int saveCategory(Category category);
    int updateCategory(Category category);
    int deleteCategory(long id);
}
