package com.dolphin.report_book.service;

import com.dolphin.report_book.entity.Appointment;
import com.dolphin.report_book.entity.Student;
import com.dolphin.report_book.entity.dto.AppointmentTable;
import com.dolphin.report_book.entity.dto.ResponseResult;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public interface AppointmentService {
    boolean insert(Appointment appointment);

    boolean deleteById(Integer id);

    Appointment getById(Integer id);

    List<Appointment> listAppointments();

    List<Appointment> listAppointments(Appointment appointment);

    int countAppointments(Appointment appointment);

    boolean update(Appointment appointment);

    boolean updatePresent(Appointment appointment);

    void exportAppointment(Integer id, HttpServletResponse response) throws IOException;

    AppointmentTable convertStudent(Student student, Appointment appointment);

    ResponseResult importAppointments(MultipartFile file);
}
