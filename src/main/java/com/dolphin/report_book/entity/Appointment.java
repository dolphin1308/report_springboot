package com.dolphin.report_book.entity;
import com.dolphin.report_book.entity.vo.MeetingVo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Appointment {
    private static final long serialVersionUID = 827721937429118051L;

    /**
     * 预约ID
     */
    private Integer id;

    /**
     * 会议ID
     */
    private Integer meetingId;

    /**
     * 学生ID
     */
    private Integer studentId;

    /**
     * 预约时间
     */
    private Date appointmentTime;

    /**
     * 是否到场【1：是】【0：否】
     */
    private Integer present;
}
