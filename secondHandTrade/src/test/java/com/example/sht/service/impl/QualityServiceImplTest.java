package com.example.sht.service.impl;

import com.example.sht.domain.Quality;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;
@RunWith(SpringRunner.class)
@SpringBootTest
class QualityServiceImplTest {

    @Autowired
    QualityServiceImpl qualityService;

    @Test
    void findAll() {
        System.out.println(qualityService.findAll());
    }

    @Test
    void findById() {
        System.out.println(qualityService.findById(1));
    }

    @Test
    void saveQuality() {
        Quality quality = new Quality();
        quality.setQuality("全新未拆封");
        System.out.println(qualityService.saveQuality(quality));
    }

    @Test
    void updateQuality() {
        Quality quality = new Quality();
        quality.setId(2);
        quality.setQuality("全新已拆封");
        System.out.println(qualityService.updateQuality(quality));
    }

    @Test
    void deleteQuality() {
        System.out.println(qualityService.deleteQuality(1));
    }
}