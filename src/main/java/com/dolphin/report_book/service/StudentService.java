package com.dolphin.report_book.service;

import com.dolphin.report_book.entity.Student;
import com.dolphin.report_book.entity.Teacher;
import com.dolphin.report_book.entity.User;
import com.dolphin.report_book.entity.dto.ResponseResult;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public interface StudentService {
    boolean insert(Student student);

    boolean deleteById(Integer id);

    Student getById(Integer id);

    Student getByNo(String no);

    List<Student> listStudents();

    List<Student> listStudents(Student student);

    int countStudents(Student student);

    boolean update(Student student);

    ResponseResult updateEmail(String email, User loginUser, Student loginStudent);

    ResponseResult updatePhone(String phone, User loginUser, Student loginStudent, Teacher loginTeacher);

    Object pageAllStudents(Integer page, Integer rows, String searchField, String searchString);

    List<Student> dealStudent(List<Student> list);

    void editStudent(Student student, String action);

    void exportStudents(HttpServletResponse response) throws IOException;

    ResponseResult importStudents(MultipartFile file);
}
