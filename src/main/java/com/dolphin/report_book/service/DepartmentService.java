package com.dolphin.report_book.service;

import com.dolphin.report_book.entity.Department;

import java.util.List;
import java.util.Map;

public interface DepartmentService {
    boolean insert(Department department);

    boolean deleteById(Integer id);

    Department getById(Integer id);

    Department getByNo(String no);

    List<Department> listDepartments();

    List<Department> listDepartments(Department department);

    int countDepartments(Department department);

    boolean update(Department department);

    Map<String, Object> pageAllDepartments(Integer page, Integer rows, String searchField, String searchString);

    List<Department> dealDepartment(List<Department> list);

    void editDepartment(Department department, String action);
}
