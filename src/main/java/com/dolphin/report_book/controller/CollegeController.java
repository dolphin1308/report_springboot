package com.dolphin.report_book.controller;

import com.alibaba.fastjson.JSONObject;
import com.dolphin.report_book.entity.College;
import com.dolphin.report_book.entity.dto.ResponseResult;
import com.dolphin.report_book.service.CollegeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/college")
public class CollegeController {
    @Autowired
    private CollegeService collegeService;
    /**
     * 查询所有学院
     */
    @GetMapping("/all")
    public ResponseResult<Object> allColleges(Integer page, Integer rows) {
//        return JSONObject.toJSONString(collegeService.pageAllColleges(page, rows));
        Object college=collegeService.pageAllColleges(page, rows);
        return ResponseResult.success(college);
    }

    /**
     * 编辑
     */
    @PostMapping("/edit")
    public ResponseResult editCollege(@RequestBody College college) {
        collegeService.editCollege(college, "edit");
        return ResponseResult.success();
    }
    /**
     * 删除
     */
    @PutMapping("/delete")
    public ResponseResult deleteCollege(@RequestBody College college){
        collegeService.editCollege(college,"del");
        return ResponseResult.success();
    }
}
