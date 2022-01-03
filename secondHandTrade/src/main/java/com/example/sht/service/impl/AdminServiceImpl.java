package com.example.sht.service.impl;

import com.example.sht.dao.AdminDao;
import com.example.sht.domain.Admin;
import com.example.sht.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminDao adminDao;
    @Override
    public List<Admin> findAll() {
        return adminDao.findAll();
    }

    @Override
    public Admin findById(long id) {
        return adminDao.findById(id);
    }

    @Override
    public int saveAdmin(Admin admin) {
        return adminDao.saveAdmin(admin);
    }

    @Override
    public int updateAdmin(Admin admin) {
        return adminDao.updateAdmin(admin);
    }

    @Override
    public int deleteAdmin(long id) {
        return adminDao.deleteAdmin(id);
    }
}
