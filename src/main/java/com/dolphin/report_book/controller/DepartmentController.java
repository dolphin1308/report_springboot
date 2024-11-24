package com.dolphin.report_book.controller;

import com.alibaba.fastjson.JSONObject;
import com.dolphin.report_book.entity.Department;
import com.dolphin.report_book.entity.dto.ResponseResult;
import com.dolphin.report_book.service.DepartmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/department")
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;
    /**
     * 查询所有部门
     */
    @GetMapping("/all")
    public ResponseResult<Object> listAll(Integer page, Integer rows, String searchField, String searchString) {
//        return JSONObject.toJSONString(departmentService.pageAllDepartments(page, rows, searchField, searchString));
        Object department=departmentService.pageAllDepartments(page, rows, searchField, searchString);
        return ResponseResult.success(department);
    }

    /**
     * 编辑
     */
    @PostMapping("/edit")
    public ResponseResult editDepartment(@RequestBody Department department) {
        departmentService.editDepartment(department, "edit");
        return ResponseResult.success();
    }
    /**
     * 删除
     */
    @PutMapping("/delete")
    public ResponseResult deleteDepartment(@RequestBody Department department){
        departmentService.editDepartment(department, "del");
        return ResponseResult.success();
    }
}
