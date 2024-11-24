package com.dolphin.report_book.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.dolphin.report_book.constant.Leader;
import com.dolphin.report_book.entity.College;
import com.dolphin.report_book.entity.Teacher;
import com.dolphin.report_book.mapper.CollegeMapper;
import com.dolphin.report_book.service.CollegeService;
import com.dolphin.report_book.service.TeacherService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Slf4j
@Service
public class CollegeServiceImpl implements CollegeService {
    @Resource
    private CollegeMapper collegeMapper;

    @Resource
    private TeacherService teacherService;

    public boolean insert(College college) {
        return collegeMapper.insert(college) == 1;
    }

    public boolean deleteById(Integer id) {
        return collegeMapper.deleteById(id) == 1;
    }

    public College getById(Integer id) {
        return collegeMapper.getById(id);
    }

    public List<College> listColleges() {
        return collegeMapper.listColleges();
    }

    public List<College> listColleges(College college) {
        return collegeMapper.listColleges(college);
    }

    public int countColleges(College college) {
        return collegeMapper.countColleges(college);
    }

    public boolean update(College college) {
        return collegeMapper.update(college) == 1;
    }

    /**
     * 分页查询所有学院
     */
    public Map<String, Object> pageAllColleges(Integer page, Integer rows) {
        Map<String, Object> map = new HashMap<>();
        // 分页查询
        PageHelper.startPage(page, rows);
        List<College> colleges = listColleges();
        PageInfo<College> pageInfo = new PageInfo<>(colleges);
        // 将查询结果放入map
        map.put("rows", dealCollege(colleges));
        // 总页数
        map.put("total", pageInfo.getPages());
        // 总条数
        map.put("records", pageInfo.getTotal());
        return map;
    }


    /**
     * 处理完善学院对象
     */
    public List<College> dealCollege(List<College> list) {
        list.forEach(college -> {
            // 设置院长
            Integer leaderId = college.getLeaderId();
            Teacher teacher = teacherService.getById(leaderId);
            college.setLeader(teacher);
        });
        return list;
    }

    /**
     * 编辑、删除学院
     */
    public void editCollege(College college, String action) {
        if ("edit".equals(action)) {
            // 编辑
            update(college);
            // 获取原来学院的信息
            College target = getById(college.getId());
            // 获取编辑学院的领导
            Teacher teacher = teacherService.getByNo(college.getLeader().getNo());
            if (ObjectUtil.isNotEmpty(teacher)) {
                if (target.getLeaderId().intValue() != teacher.getId()) {
                    // 更换领导
                    Integer oldLeaderId = target.getLeaderId();
                    Teacher oldLeader = Teacher.builder().id(oldLeaderId).isCollegeLeader(Leader.NO).build();
                    teacherService.update(oldLeader);
                    // 设置新领导
                    teacher.setIsCollegeLeader(Leader.YES);
                    teacherService.update(teacher);
                    College build = College.builder().id(college.getId()).leaderId(teacher.getId()).build();
                    update(build);
                    log.info("学院 {} 设置新领导 -> {}", target.getId(), teacher.getNo());
                }
            }
        }
        if ("del".equals(action)) {
            // 删除
            deleteById(college.getId());
        }
    }
}
