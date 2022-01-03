package com.example.sht.service;

import com.example.sht.domain.Category;
import com.example.sht.domain.Quality;
import com.example.sht.domain.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface QualityService {
    List<Quality> findAll();
    List<Quality> findByName(String quality);
    Quality findById(long id);
    int saveQuality(Quality quality);
    int updateQuality(Quality quality);
    int deleteQuality(long id);
}
