package com.example.sht.controller;

import com.example.sht.domain.Message;
import com.example.sht.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/message")
public class MessageController {
    @Autowired
    MessageService messageService;

    @GetMapping("/{id}")
    List<Message> getMessageList(@PathVariable long id, @RequestParam("dstUserId") long dstUserId){
        return messageService.findByUserId(id, dstUserId);
    }
    @PostMapping("/{id}")
    int saveMessage(Message message){
        return messageService.saveMessage(message);
    }
}
