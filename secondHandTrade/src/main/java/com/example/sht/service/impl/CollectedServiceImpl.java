package com.example.sht.service.impl;

import com.example.sht.dao.CollectedDao;
import com.example.sht.domain.Collected;
import com.example.sht.service.CollectedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CollectedServiceImpl implements CollectedService {

    @Autowired
    private CollectedDao collectedDao;
    @Override
    public List<Collected> findAll() {
        return collectedDao.findAll();
    }

    @Override
    public List<Collected> findByUserId(long userId) {
        return collectedDao.findByUserId(userId);
    }

    @Override
    public Collected findById(long id) {
        return collectedDao.findById(id);
    }

    @Override
    public int saveCollected(Collected collected) {
        return collectedDao.saveCollected(collected);
    }

    @Override
    public int updateCollected(Collected collected) {
        return collectedDao.updateCollected(collected);
    }

    @Override
    public int deleteCollected(long id) {
        return collectedDao.deleteCollected(id);
    }
}
