package com.example.sht.dao;

import com.example.sht.domain.Admin;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AdminDao {
    List<Admin> findAll();
    Admin findById(long id);
    int saveAdmin(Admin admin);
    int updateAdmin(Admin admin);
    int deleteAdmin(long id);
}
