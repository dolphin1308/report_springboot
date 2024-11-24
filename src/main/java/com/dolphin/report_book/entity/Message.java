package com.dolphin.report_book.entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Message implements Serializable {
    private static final long serialVersionUID = -82948769078351684L;

    /**
     * 主键 ID
     */
    private Integer id;

    /**
     * 消息教师ID
     */
    private Integer toTeacherId;

    /**
     * 消息内容
     */
    private String message;

    /**
     * 发送时间
     */
    private Date sendTime;
}
