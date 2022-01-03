package com.example.sht.service;

import com.example.sht.domain.Message;
import com.example.sht.domain.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MessageService {
    List<Message> findAll();
    List<Message> findByUserId(long userId,long dstUserId);
    List<Message> findMessageUserListByUserId(long userId);
    Message findById(long id);
    int saveMessage(Message message);
    int updateMessage(Message message);
    int deleteMessage(long id);
}
