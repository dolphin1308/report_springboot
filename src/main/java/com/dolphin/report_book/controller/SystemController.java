package com.dolphin.report_book.controller;

import com.dolphin.report_book.constant.ReportConstant;
import com.dolphin.report_book.constant.Role;
import com.dolphin.report_book.entity.*;
import com.dolphin.report_book.entity.dto.ResponseResult;
import com.dolphin.report_book.service.*;
import com.dolphin.report_book.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@RestController
@RequestMapping
public class SystemController {
    @Autowired
    private AdminService adminService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private ReportService reportService;
    @Autowired
    private MeetingService meetingService;


    @GetMapping("/profile")
    public ResponseResult profile(@RequestHeader("Authorization") String token){
        Map<String,Object> map= JwtUtil.parseToken(token);
        String no=(String) map.get("no");
        String role=(String) map.get("role");
        if(Role.STUDENT.equals(role)){
            Student student=studentService.getByNo(no);
            return ResponseResult.success(student);
        }
        if(Role.TEACHER.equals(role)){
            Teacher teacher=teacherService.getByNo(no);
            return ResponseResult.success(teacher);
        }
        if(Role.DEPARTMENT.equals(role)){
            Department department=departmentService.getByNo(no);
            return ResponseResult.success(department);
        }
        return ResponseResult.error("获取类型错误");
    }

    /**
     * 学生查看可以开放预约的报告
     */
    @GetMapping("/appointing-meeting")
    public ResponseResult<Object> appointingMeeting(@RequestHeader("Authorization") String token){
        Map<String,Object> map= JwtUtil.parseToken(token);
        String no=(String) map.get("no");
        Student student=studentService.getByNo(no);
        List<Report> waitMeeting=reportService.listReportsByCollegeId(student.getCollegeId(), ReportConstant.APPOINTMENT);
        List<Report> result = new ArrayList<>(waitMeeting);
        return ResponseResult.success(result);
    }
    /**
     * 学生查看自己的预约记录
     */
    @GetMapping("/my-appointment")
    public ResponseResult<Object> myAppointment(@RequestHeader("Authorization") String token){
        Map<String,Object> map = JwtUtil.parseToken(token);
        Integer id=(Integer) map.get("id");
        List<Meeting> meetings = meetingService.listAppointingMeeting(id);
        return ResponseResult.success(meetings);
    }
    /**
     * 查看历史预约记录
     */
    @GetMapping("/my-present")
    public ResponseResult<List<Meeting>> myPresent(@RequestHeader("Authorization") String token){
        Map<String,Object> map = JwtUtil.parseToken(token);
        List<Meeting> meetings = meetingService.listAppointingFinishMeeting((Integer) map.get("id"));
        return ResponseResult.success(meetings);
    }
    /**
     * 教师查看自己的报告
     */
    @GetMapping("/my-report")
    public ResponseResult<List<Report>> reports(@RequestHeader("Authorization") String token) {
        Map<String,Object> map = JwtUtil.parseToken(token);

        Report build = Report.builder()
                .reporterNo((String) map.get("no"))
                .build();
        // 查询我的全部报告
        List<Report> reports = reportService.listReports(build);
        // 过滤掉回收站状态的报告
        reports = reports.stream()
                .filter(report -> report.getStatus().intValue() != ReportConstant.TRASH)
                .collect(Collectors.toList());
        return ResponseResult.success(reports);
    }

}
