package com.dolphin.report_book.entity.vo;

import com.dolphin.report_book.entity.Appointment;
import com.dolphin.report_book.entity.Student;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AppointmentVo {
    private Student student;
    private MeetingVo meetingVo;
    private Appointment appointment;
}