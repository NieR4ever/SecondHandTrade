package com.example.sht.service.impl;

import com.example.sht.dao.MessageDao;
import com.example.sht.domain.Message;
import com.example.sht.domain.User;
import com.example.sht.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageDao messageDao;

    @Override
    public List<Message> findAll() {
        return messageDao.findAll();
    }

    @Override
    public List<Message> findByUserId(long userId,long dstUserId) {
        return messageDao.findByUserId(userId,dstUserId);
    }

    @Override
    public List<Message> findMessageUserListByUserId(long userId) {
        List<Message> messageUserList = messageDao.findMessageUserListByUserId(userId);
        for (int i = 0; i < messageUserList.size(); i++) {
            for (int j = i+1; j < messageUserList.size(); j++) {
                if (messageUserList.get(i).getSrcUser().getId() == messageUserList.get(j).getDstUser().getId()
                    && messageUserList.get(j).getSrcUser().getId() == messageUserList.get(i).getDstUser().getId()){
                    messageUserList.remove(j);
                }
            }
            User dstUser = messageUserList.get(i).getDstUser();
            User srcUser = messageUserList.get(i).getSrcUser();
            if (dstUser.getId()==userId){
                messageUserList.get(i).setDstUser(srcUser);
                messageUserList.get(i).setSrcUser(dstUser);
            }
        }
        return messageUserList;
    }


    @Override
    public Message findById(long id) {
        return messageDao.findById(id);
    }

    @Override
    public int saveMessage(Message message) {
        return messageDao.saveMessage(message);
    }

    @Override
    public int updateMessage(Message message) {
        return messageDao.updateMessage(message);
    }

    @Override
    public int deleteMessage(long id) {
        return messageDao.deleteMessage(id);
    }
}
