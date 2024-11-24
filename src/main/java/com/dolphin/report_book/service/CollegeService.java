package com.dolphin.report_book.service;

import com.dolphin.report_book.entity.College;

import java.util.List;
import java.util.Map;

public interface CollegeService {
    boolean insert(College college);

    boolean deleteById(Integer id);

    College getById(Integer id);

    List<College> listColleges();

    List<College> listColleges(College college);

    int countColleges(College college);

    boolean update(College college);

    Map<String, Object> pageAllColleges(Integer page, Integer rows);

    List<College> dealCollege(List<College> list);

    void editCollege(College college, String action);
}
