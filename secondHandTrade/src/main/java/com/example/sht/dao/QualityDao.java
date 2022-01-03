package com.example.sht.dao;

import com.example.sht.domain.Quality;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface QualityDao {
    List<Quality> findAll();
    List<Quality> findByName(String quality);
    Quality findById(long id);
    int saveQuality(Quality quality);
    int updateQuality(Quality quality);
    int deleteQuality(long id);
}
