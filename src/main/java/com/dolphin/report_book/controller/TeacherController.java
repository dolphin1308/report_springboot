package com.dolphin.report_book.controller;

import com.alibaba.fastjson.JSONObject;
import com.dolphin.report_book.entity.Teacher;
import com.dolphin.report_book.entity.dto.ResponseResult;
import com.dolphin.report_book.service.TeacherService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;
@Slf4j
@RestController
@RequestMapping("/teacher")
public class TeacherController {
    @Autowired
    private TeacherService teacherService;
    /**
     * 查询所有教师
     */
    @GetMapping("/all")
    public ResponseResult<Object> listAll(Integer page, Integer rows, String searchField, String searchString) {
        Object teacher=teacherService.pageAllTeachers(page, rows, searchField, searchString);
        return ResponseResult.success(teacher);
    }

    /**
     * 编辑
     */
    @PostMapping("/edit")
    public ResponseResult editTeacher(@RequestBody Teacher teacher) {
        teacherService.editTeacher(teacher, "edit");
        return ResponseResult.success();
    }
    /**
     * 删除
     */
    @PutMapping("/delete")
    public ResponseResult deleteTeacher(@RequestBody Teacher teacher){
        teacherService.editTeacher(teacher,"del");
        return ResponseResult.success();
    }

    /**
     * 导出Excel
     */
    @GetMapping("/exportTeachers")
    public void exportTeachers(HttpServletResponse response) throws IOException {
        teacherService.exportTeachers(response);
    }

    /**
     * 批量导入教师
     */
    @PostMapping("/importTeachers")
    @Transactional
    public ResponseResult importTeachers(MultipartFile file) {
        ResponseResult result=new ResponseResult();
        log.info("接收到文件：{}", file.getOriginalFilename());
        if (!Objects.requireNonNull(file.getOriginalFilename()).endsWith(".xls")) {
            result.setCode(301);
            result.setMessage("请上传【.xls】文件");
            return result;
        }
        return teacherService.importTeachers(file);
    }
}
