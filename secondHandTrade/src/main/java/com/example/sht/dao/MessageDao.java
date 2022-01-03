package com.example.sht.dao;

import com.example.sht.domain.Message;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MessageDao {
    List<Message> findAll();
    List<Message> findByUserId(long userId,long dstUserId);
    Message findById(long id);
    int saveMessage(Message message);
    int updateMessage(Message message);
    int deleteMessage(long id);
    List<Message> findMessageUserListByUserId(long userId);
}
