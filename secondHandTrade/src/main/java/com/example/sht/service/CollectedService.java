package com.example.sht.service;

import com.example.sht.domain.Collected;
import com.example.sht.domain.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CollectedService {
    List<Collected> findAll();
    List<Collected> findByUserId(long userId);
    Collected findById(long id);
    int saveCollected(Collected collected);
    int updateCollected(Collected collected);
    int deleteCollected(long id);
}
