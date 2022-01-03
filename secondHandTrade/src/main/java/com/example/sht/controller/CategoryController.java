package com.example.sht.controller;

import com.example.sht.domain.Category;
import com.example.sht.domain.Data;
import com.example.sht.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * 分类的controller
 */
@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    Data data = null;
    HashMap<String, Object> map = null;

    /**
     * 获取全部分类
     * @return
     */
    @GetMapping("/findAllCategories")
    public Data getCategoryList(){
        data = new Data();
        map = new HashMap<>();
        List<Category> categories = categoryService.findAll();
        map.put("categories",categories);
        data.setData(map);
        data.setMsg("查询成功");
        return data;
    }

    /**
     * 根据id获取分类
     * @param id
     * @return
     */
    @GetMapping("{id}")
    public Data getCategoryById(@PathVariable("id") int id){
        data = new Data();
        map = new HashMap<>();
        Category category = categoryService.findById(id);
        map.put("category",category);
        data.setData(map);
        data.setMsg("查询成功");
        return data;
    }

    /**
     * 根据名字获取分类
     * @param category
     * @return
     */
    @GetMapping("/findCategoriesByName")
    public Data getCategoryListByName(@RequestParam("category") String category) {
        data = new Data();
        map = new HashMap<>();
        List<Category> categories = categoryService.findByName(category);
        if (categories != null){
            if (categories.size()!=0){
                map.put("categories",categories);
                data.setData(map);
                data.setMsg("查询成功");
            } else {
                map.put("categories",categories);
                data.setData(map);
                data.setErrorCode(2);
                data.setMsg("没有这个分类");
            }
        } else {
            map.put("categories", Collections.singletonList(new Category()));
            data.setData(map);
            data.setErrorCode(1);
            data.setMsg("查询失败");
        }
        return data;
    }

    /**
     * 保存一个分类
     * @param category
     * @return
     */
    @PostMapping("/doCategory")
    public Data saveCategory(@RequestParam("category") String category) {
        data = new Data();
        map = new HashMap<>();
        Category categoryForSave = new Category();
        categoryForSave.setCategory(category);
        int i = categoryService.saveCategory(categoryForSave);
        if (i > 0) {
            map.put("categoryOperation",i);
            data.setMsg("保存成功");
            data.setData(map);
        } else {
            data.setMsg("保存失败");
            data.setErrorCode(1);
        }
        return data;
    }

    /**
     * 更新一个分类
     * @param id
     * @return
     */
    @PutMapping("/doCategory")
    public Data updateCategory(int id) {
        data = new Data();
        map = new HashMap<>();
        Category categoryForUpdate = new Category();
        categoryForUpdate.setId(id);
        int i = categoryService.updateCategory(categoryForUpdate);
        if (i > 0) {
            map.put("categoryOperation",i);
            data.setMsg("修改成功");
            data.setData(map);
        } else {
            data.setMsg("修改失败");
            data.setErrorCode(1);
        }
        return data;
    }

    /**
     * 删除一个分类
     * @param id
     * @return
     */
    @DeleteMapping("/doCategory")
    public Data deleteCategory(int id) {
        data = new Data();
        map = new HashMap<>();
        int i = categoryService.deleteCategory(id);
        if (i > 0) {
            map.put("categoryOperation",i);
            data.setMsg("删除成功");
            data.setData(map);
        } else {
            data.setMsg("删除失败");
            data.setErrorCode(1);
        }
        return data;
    }
}
