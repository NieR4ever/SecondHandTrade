package com.example.sht.service.impl;

import com.example.sht.dao.CategoryDao;
import com.example.sht.domain.Category;
import com.example.sht.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryDao categoryDao;
    @Override
    public List<Category> findAll() {
        return categoryDao.findAll();
    }

    @Override
    public List<Category> findByName(String category) {
        return categoryDao.findByName(category);
    }

    @Override
    public Category findById(long id) {
        return categoryDao.findById(id);
    }

    @Override
    public int saveCategory(Category category) {
        return categoryDao.saveCategory(category);
    }

    @Override
    public int updateCategory(Category category) {
        return categoryDao.updateCategory(category);
    }

    @Override
    public int deleteCategory(long id) {
        return categoryDao.deleteCategory(id);
    }
}
