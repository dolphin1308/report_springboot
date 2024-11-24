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
import org.springframework.data.redis.core.RedisTemplate;
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
                System.out.println("LoginController---->admins:"+admin);
                Map <String,Object> claims=new HashMap<>();
                claims.put("id",admin.getId());
                claims.put("no",admin.getNo());
                String token= JwtUtil.genToken(claims);
                ValueOperations<String,String >valueOps= redisTemplate.opsForValue();
                valueOps.set(token,token,1, TimeUnit.HOURS);
                return ResponseResult.success(token);

                // 找到登录的用户并放入session
//                Admin admin = admins.get(0);
//                user = User.builder()
//                        .id(admin.getId())
//                        .no(no)
//                        .name("管理员[" + admin.getId() + "]")
//                        .role(Role.ADMIN)
//                        .img(admin.getImg())
//                        .build();
//                result.setCode(200);
//                result.setMessage("管理员登录成功");
            }
//            else {
//                // 判断账号是否存在
//                build.setPassword(null);
//                List<Admin> existAdmin = adminService.listAdmins(build);
//                if (existAdmin.size() > 0) {
//                    // 密码错误
//                    result.setCode(100);
//                    result.setMessage("密码错误");
//                } else {
//                    // 用户不存在
//                    result.setCode(101);
//                    result.setMessage("用户不存在");
//                }
//            }
        }
        if (Role.STUDENT.equals(role)) {
            // 学生登录
            Student build = Student.builder()
                    .no(no).password(password)
                    .build();
            List<Student> students = studentService.listStudents(build);
            if (students.size() > 0) {
                // 找到登录的用户并放入session
                Student student = students.get(0);
                user = User.builder()
                        .id(student.getId())
                        .no(no)
                        .name(student.getName())
                        .role(Role.STUDENT)
                        .img(student.getImg())
                        .build();
                result.setCode(200);
                result.setMessage("学生登录成功");
            } else {
                // 判断账号是否存在
                build.setPassword(null);
                List<Student> existStudent = studentService.listStudents(build);
                if (existStudent.size() > 0) {
                    // 密码错误
                    result.setCode(100);
                    result.setMessage("密码错误");
                } else {
                    // 用户不存在
                    result.setCode(101);
                    result.setMessage("用户不存在");
                }
            }
        }
        if (Role.TEACHER.equals(role)) {
            // 教师登录
            Teacher build = Teacher.builder()
                    .no(no).password(password)
                    .build();
            List<Teacher> teachers = teacherService.listTeachers(build);
            if (teachers.size() > 0) {
                // 找到登录的用户并放入session
                Teacher teacher = teachers.get(0);
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
            } else {
                // 判断账号是否存在
                build.setPassword(null);
                List<Teacher> existTeacher = teacherService.listTeachers(build);
                if (existTeacher.size() > 0) {
                    // 密码错误
                    result.setCode(100);
                    result.setMessage("密码错误");
                } else {
                    // 用户不存在
                    result.setCode(101);
                    result.setMessage("用户不存在");
                }
            }
        }
        if (Role.DEPARTMENT.equals(role)) {
            // 部门登录
            Department build = Department.builder()
                    .no(no).password(password)
                    .build();
            List<Department> departments = departmentService.listDepartments(build);
            if (departments.size() > 0) {
                // 找到登录的用户并放入session
                Department department = departments.get(0);
                user = User.builder()
                        .id(department.getId())
                        .no(no)
                        .name(department.getName())
                        .role(Role.DEPARTMENT)
                        .img(department.getImg())
                        .build();
                result.setCode(200);
                result.setMessage("部门登录成功");
            } else {
                // 判断账号是否存在
                build.setPassword(null);
                List<Department> existDepartment = departmentService.listDepartments(build);
                if (existDepartment.size() > 0) {
                    // 密码错误
                    result.setCode(100);
                    result.setMessage("密码错误");
                } else {
                    // 用户不存在
                    result.setCode(101);
                    result.setMessage("用户不存在");
                }
            }
        }
//        session.setAttribute("loginUser", user);
        return result;
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
}
