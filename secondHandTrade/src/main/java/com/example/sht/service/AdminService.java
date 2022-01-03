package com.example.sht.service;

import com.example.sht.domain.Admin;
import com.example.sht.domain.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AdminService {
    List<Admin> findAll();
    Admin findById(long id);
    int saveAdmin(Admin admin);
    int updateAdmin(Admin admin);
    int deleteAdmin(long id);
}
