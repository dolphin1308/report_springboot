package com.dolphin.report_book.service.impl;

import com.dolphin.report_book.entity.Message;
import com.dolphin.report_book.mapper.MessageMapper;
import com.dolphin.report_book.service.MessageService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {
    @Resource
    private MessageMapper messageMapper;

    public boolean insert(Message message) {
        return messageMapper.insert(message) == 1;
    }


    public boolean deleteById(Integer id) {
        return messageMapper.deleteById(id) == 1;
    }


    public boolean deleteAllByTeacherId(Integer id) {
        return messageMapper.deleteAllByTeacherId(id);
    }


    public Message getById(Integer id) {
        return messageMapper.getById(id);
    }


    public List<Message> listMessages() {
        return messageMapper.listMessages();
    }


    public List<Message> listMessages(Message message) {
        return messageMapper.listMessages(message);
    }


    public int countMessages(Message message) {
        return messageMapper.countMessages(message);
    }


    public boolean update(Message message) {
        return messageMapper.update(message) == 1;
    }

}
