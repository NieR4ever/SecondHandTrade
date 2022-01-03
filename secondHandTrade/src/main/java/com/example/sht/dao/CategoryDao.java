package com.example.sht.dao;

import com.example.sht.domain.Category;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CategoryDao {
    List<Category> findAll();
    List<Category> findByName(String category);
    Category findById(long id);
    int saveCategory(Category category);
    int updateCategory(Category category);
    int deleteCategory(long id);
}
