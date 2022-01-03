package com.example.sht.service.impl;

import com.example.sht.domain.Message;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@RunWith(SpringRunner.class)
@SpringBootTest
class MessageServiceImplTest {
    @Autowired
    MessageServiceImpl messageService;
    @Autowired
    UserServiceImpl userService;
    @Test
    void findAll() {
        System.out.println(messageService.findAll());
    }

    @Test
    void findById() {
        System.out.println(messageService.findById(1));
    }

    @Test
    void saveMessage() {
        Message message = new Message();
        message.setContent("你也好");
        message.setSrcUser(userService.findById(4));
        message.setDstUser(userService.findById(2));
        System.out.println(messageService.saveMessage(message));
    }

    @Test
    void updateMessage() {
        Message message = new Message();
        message.setId(1);
        message.setContent("再见");
        message.setSrcUser(userService.findById(2));
        message.setDstUser(userService.findById(4));
        System.out.println(messageService.updateMessage(message));
    }

    @Test
    void deleteMessage() {
        System.out.println(messageService.deleteMessage(1));
    }

    @Test
    void findByUserId() {
        Message message = new Message();
        message.setSrcUser(userService.findById(2));
        message.setDstUser(userService.findById(4));
//        System.out.println(messageService.findByUserId(message));
    }

    @Test
    void findMessageUserListByUserId() {
        List<Message> messageUserListByUserId = messageService.findMessageUserListByUserId(5);
        for (Message message : messageUserListByUserId) {
            System.out.println(message);
        }
    }
}