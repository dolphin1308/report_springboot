package com.dolphin.report_book.controller;

import com.dolphin.report_book.constant.Leader;
import com.dolphin.report_book.constant.Role;
import com.dolphin.report_book.entity.*;
import com.dolphin.report_book.entity.dto.ResponseResult;
import com.dolphin.report_book.service.AdminService;
import com.dolphin.report_book.service.DepartmentService;
import com.dolphin.report_book.service.StudentService;
import com.dolphin.report_book.service.TeacherService;
import com.dolphin.report_book.utils.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.redis.core.StringRedisTemplate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Slf4j
@RestController
@RequestMapping("/report")
public class LoginController {
    @Autowired
    private AdminService adminService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private StringRedisTemplate redisTemplate;
    /**
     * 登录
     */
    @PostMapping("/login")
    @ResponseBody
    public ResponseResult login(String no, String password, String role) {
        ResponseResult result=new ResponseResult();
//        log.info("请求登录的用户信息 : {}", "no = " + no + ", password = " + password + ", role = " + role);
        User user = null;
        if (Role.ADMIN.equals(role)) {
            // 管理员登录
            Admin admin = adminService.getByNo(no);
            if (admin!=null && admin.getPassword().equals(password)) {
                return LoginSetting(admin.getId(), admin.getNo(),Role.ADMIN);
            }
            else{
                return ResponseResult.error("管理员登陆失败！");
            }
        }
        if (Role.STUDENT.equals(role)) {
            // 学生登录
            Student students = studentService.getByNo(no);
            if (students!=null && students.getPassword().equals(password)) {
                return LoginSetting(students.getId(), students.getNo(),Role.STUDENT);
            }
            else{
                return ResponseResult.error("学生登陆失败！");
            }
        }
        if (Role.TEACHER.equals(role)) {
            // 教师登录
            Teacher teacher = teacherService.getByNo(no);
            if (teacher!=null && teacher.getPassword().equals(password)) {
                user = User.builder()
                        .id(teacher.getId())
                        .no(no)
                        .name(teacher.getName())
                        .role(Role.TEACHER)
                        .img(teacher.getImg())
                        .build();
                result.setCode(200);
                result.setMessage("教师登录成功");
                if (teacher.getIsCollegeLeader().intValue() == Leader.YES) {
                    // 学院领导
                    user.setIsLeader(Leader.YES);
                }
                return LoginSetting(teacher.getId(), teacher.getNo(),Role.TEACHER);
            } else {
                return ResponseResult.error("教师登陆失败！");
            }
        }
        if (Role.DEPARTMENT.equals(role)) {
            // 部门登录
            Department department = departmentService.getByNo(no);
            if (department!=null && department.getPassword().equals(password)) {
                // 找到登录的用户并放入session
                return LoginSetting(department.getId(), department.getNo(),Role.DEPARTMENT);
            } else {
                return ResponseResult.error("部门登陆失败！");
            }
        }
        return ResponseResult.error("登陆用户类型错误！");
    }
    /**
     * 退出登录
     */
    @GetMapping("/logout")
    public String logout() {
        // 清空session
//        session.invalidate();
        return "redirect:/login.html";
    }

//    /**
//     * 修改密码
//     */
//    @PostMapping("/reset")
//    @ResponseBody
//    public ResponseResult reset(String oldPassword, String newPassword) {
////        ColorStream.Out("oldPassword:"+oldPassword+"newPassword:"+newPassword);
////        String role = loginUser.getRole();
//        if (Role.ADMIN.equals(role)) {
//            // 管理员登录
//            if (loginAdmin.getPassword().equals(oldPassword)) {
//                // 旧密码正确
//                loginAdmin.setPassword(newPassword);
//                if (adminService.update(loginAdmin)) {
//                    result.setCode(200);
//                    result.setMessage("修改密码成功");
//                }
//            } else {
//                result.setCode(501);
//                result.setMessage("原密码错误");
//            }
//        }
//        if (Role.STUDENT.equals(role)) {
//            // 学生登录
//            if (loginStudent.getPassword().equals(oldPassword)) {
//                // 旧密码正确
//                loginStudent.setPassword(newPassword);
//                if (studentService.update(loginStudent)) {
//                    result.setCode(200);
//                    result.setMessage("修改密码成功");
//                }
//            } else {
//                result.setCode(501);
//                result.setMessage("原密码错误");
//            }
//        }
//        if (Role.TEACHER.equals(role)) {
//            // 教师登录
//            if (loginTeacher.getPassword().equals(oldPassword)) {
//                // 旧密码正确
//                loginTeacher.setPassword(newPassword);
//                if (teacherService.update(loginTeacher)) {
//                    result.setCode(200);
//                    result.setMessage("修改密码成功");
//                }
//            } else {
//                result.setCode(501);
//                result.setMessage("原密码错误");
//            }
//        }
//        if (Role.DEPARTMENT.equals(role)) {
//            // 部门登录
//            if (loginDept.getPassword().equals(oldPassword)) {
//                // 旧密码正确
//                loginDept.setPassword(newPassword);
//                if (departmentService.update(loginDept)) {
//                    result.setCode(200);
//                    result.setMessage("修改密码成功");
//                }
//            } else {
//                result.setCode(501);
//                result.setMessage("原密码错误");
//            }
//        }
//        // 修改成功清空session
//        if (result.getCode() == 200) {
//            session.invalidate();
//        }
//        return result;
//    }
    public ResponseResult LoginSetting(Integer id,String no,String role){
        Map<String,Object> claims=new HashMap<>();
        claims.put("id",id);
        claims.put("no",no);
        claims.put("role",role);
        String token=JwtUtil.genToken(claims);
        ValueOperations<String,String> valueOps=redisTemplate.opsForValue();
        valueOps.set(token,token,1,TimeUnit.HOURS);
        return ResponseResult.success(token);
    }
}
