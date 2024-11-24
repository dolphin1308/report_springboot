package com.dolphin.report_book.controller;

import com.dolphin.report_book.entity.User;
import com.dolphin.report_book.entity.dto.ResponseResult;
import com.dolphin.report_book.service.MeetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

public class MeetingController {
    @Autowired
    private MeetingService meetingService;
    /**
     * 安排报告会议
     */
    @PostMapping("/arrangeMeeting")
    public ResponseResult arrangeMeeting(@RequestParam("id") Integer id, @RequestParam("hostNo") String hostNo,
                                         @RequestParam("reportTime") String reportTime, @RequestParam("reportAddress") String reportAddress,
                                         @RequestParam("appointEnd") String appointEnd, @RequestParam("capacity") Integer capacity) {
        return meetingService.arrangeMeeting(id, hostNo, reportTime, reportAddress, appointEnd, capacity);
    }

    /**
     * 更新报告会议
     */
    @PostMapping("/updateMeeting")
    public ResponseResult updateMeeting(@RequestParam("meetingId") Integer meetingId, @RequestParam("hostNo") String hostNo,
                                        @RequestParam("reportTime") String reportTime, @RequestParam("reportAddress") String reportAddress,
                                        @RequestParam("appointEnd") String appointEnd, @RequestParam("capacity") Integer capacity) {
        return meetingService.updateMeeting(meetingId, hostNo, reportTime, reportAddress, appointEnd, capacity);
    }

    /**
     * 学生预约会议
     */
    @PostMapping("/applyMeeting")
    public ResponseResult applyMeeting(@RequestParam("id") Integer id) {
        /**
         * flag
         */
        User user=new User(1,"20171101",null,null,null,null,0);
        return meetingService.applyMeeting(id,user);
    }
}
