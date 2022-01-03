package com.example.sht.dao;

import com.example.sht.domain.Collected;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CollectedDao {
    List<Collected> findAll();
    List<Collected> findByUserId(long userId);
    Collected findById(long id);
    int saveCollected(Collected collected);
    int updateCollected(Collected collected);
    int deleteCollected(long id);
}
