package com.dolphin.report_book.service;

import com.dolphin.report_book.entity.Teacher;
import com.dolphin.report_book.entity.dto.ResponseResult;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface TeacherService {
    boolean insert(Teacher teacher);

    boolean deleteById(Integer id);

    Teacher getById(Integer id);

    Teacher getByNo(String no);

    List<Teacher> listTeachers();

    List<Teacher> listTeachers(Teacher teacher);

    int countTeachers(Teacher teacher);

    boolean update(Teacher teacher);

    Map<String, Object> pageAllTeachers(Integer page, Integer rows, String searchField, String searchString);

    List<Teacher> dealTeacher(List<Teacher> list);

    Teacher dealTeacher(Teacher teacher);

    void editTeacher(Teacher teacher, String action);

    void exportTeachers(HttpServletResponse response) throws IOException;

    ResponseResult importTeachers(MultipartFile file);
}
