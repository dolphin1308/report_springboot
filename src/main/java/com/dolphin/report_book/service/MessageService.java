package com.dolphin.report_book.service;

import com.dolphin.report_book.entity.Message;

import java.util.List;

public interface MessageService {
    boolean insert(Message message);

    boolean deleteById(Integer id);

    boolean deleteAllByTeacherId(Integer id);

    Message getById(Integer id);

    List<Message> listMessages();

    List<Message> listMessages(Message message);

    int countMessages(Message message);

    boolean update(Message message);
}
