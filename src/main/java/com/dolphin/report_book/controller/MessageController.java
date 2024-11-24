package com.dolphin.report_book.controller;

import com.dolphin.report_book.entity.dto.ResponseResult;
import com.dolphin.report_book.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Messages")
public class MessageController {
    @Autowired
    private MessageService messageService;
    /**
     * 全部删除
     */
    @PostMapping("/deleteAll")
    public ResponseResult deleteAllMessages() {
        ResponseResult result=new ResponseResult();
        /**
         * flag
         */
        if (messageService.deleteAllByTeacherId(1)) {
            result.setCode(200);
            result.setMessage("清空成功");
        } else {
            result.setCode(500);
            result.setMessage("没有消息被清空");
        }
        return result;
    }
}
