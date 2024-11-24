package com.dolphin.report_book.controller;

import com.alibaba.fastjson.JSONObject;
import com.dolphin.report_book.entity.Student;
import com.dolphin.report_book.entity.dto.ResponseResult;
import com.dolphin.report_book.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@Slf4j
@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private StudentService studentService;
    /**
     * 修改邮箱
     */
//    @PostMapping("/updateEmail")
//    public ResponseResult updateEmail(@RequestParam("email") String email) {
//        return studentService.updateEmail(email, loginUser, loginStudent);
//    }

    /**
     * 修改手机号
     */
//    @PostMapping("/updatePhone")
//    public ResponseResult updatePhone(@RequestParam("phone") String phone) {
//        return studentService.updatePhone(phone, loginUser, loginStudent, loginTeacher);
//    }

    /**
     * 查询所有学生
     */
    @GetMapping("/all")
    public ResponseResult<Object> listAll(Integer page, Integer rows, String searchField, String searchString) {
        Object student=studentService.pageAllStudents(page, rows, searchField, searchString);
        return ResponseResult.success(student);
    }

    /**
     * 编辑
     */
    @PostMapping("/edit")
    public ResponseResult editStudent(@RequestBody Student student) {
        studentService.editStudent(student, "edit");
        return ResponseResult.success();
    }

    /**
     * 删除
     */
    @PutMapping("/delete")
    public ResponseResult deleteStudent(@RequestBody Student student){
        studentService.editStudent(student, "del");
        return ResponseResult.success();
    }

    /**
     * 导出Excel
     */
    @GetMapping("/exportStudents")
    public void exportStudents(HttpServletResponse response) throws IOException {
        studentService.exportStudents(response);
    }

    /**
     * 批量导入学生
     */
    @PostMapping("/importStudents")
    @Transactional
    public ResponseResult importStudents(MultipartFile file) {
        ResponseResult result=new ResponseResult();
        log.info("接收到文件：{}", file.getOriginalFilename());
        if (!file.getOriginalFilename().endsWith(".xls")) {
            result.setCode(301);
            result.setMessage("请上传【.xls】文件");
            return result;
        }
        return studentService.importStudents(file);
    }
}
