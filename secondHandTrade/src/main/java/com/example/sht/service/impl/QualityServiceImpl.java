package com.example.sht.service.impl;

import com.example.sht.dao.QualityDao;
import com.example.sht.domain.Category;
import com.example.sht.domain.Quality;
import com.example.sht.service.QualityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QualityServiceImpl implements QualityService {

    @Autowired
    private QualityDao qualityDao;
    @Override
    public List<Quality> findAll() {
        return qualityDao.findAll();
    }

    @Override
    public List<Quality> findByName(String quality) {
        return qualityDao.findByName(quality);
    }

    @Override
    public Quality findById(long id) {
        return qualityDao.findById(id);
    }

    @Override
    public int saveQuality(Quality quality) {
        return qualityDao.saveQuality(quality);
    }

    @Override
    public int updateQuality(Quality quality) {
        return qualityDao.updateQuality(quality);
    }

    @Override
    public int deleteQuality(long id) {
        return qualityDao.deleteQuality(id);
    }
}
