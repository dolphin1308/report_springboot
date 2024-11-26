package com.dolphin.report_book.controller;

import com.dolphin.report_book.entity.User;
import com.dolphin.report_book.entity.dto.ResponseResult;
import com.dolphin.report_book.service.MeetingService;
import com.dolphin.report_book.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping
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
    public ResponseResult applyMeeting(@RequestParam("id") Integer id,
                                       @RequestHeader("Authorization") String token) {
        /**
         * flag
         *
         * User(id=1, no=20171101, name=薛伟, role=student, img=https://xuewei-blog.oss-cn-beijing.aliyuncs.com/202312211114310.png, college=College(id=1, name=计算机科学与工程学院, leaderId=6, leader=null), isLeader=0)
         */
        Map<String,Object> map = JwtUtil.parseToken(token);
        Integer userId=(Integer) map.get("id");
        return meetingService.applyMeeting(id,userId);
    }
}
