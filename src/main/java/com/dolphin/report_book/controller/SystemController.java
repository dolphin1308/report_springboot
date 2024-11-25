package com.dolphin.report_book.controller;

import com.dolphin.report_book.constant.Role;
import com.dolphin.report_book.entity.Admin;
import com.dolphin.report_book.entity.Department;
import com.dolphin.report_book.entity.Student;
import com.dolphin.report_book.entity.Teacher;
import com.dolphin.report_book.entity.dto.ResponseResult;
import com.dolphin.report_book.service.AdminService;
import com.dolphin.report_book.service.DepartmentService;
import com.dolphin.report_book.service.StudentService;
import com.dolphin.report_book.service.TeacherService;
import com.dolphin.report_book.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;


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
}
