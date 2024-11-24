package com.dolphin.report_book.service;

import com.dolphin.report_book.entity.Meeting;
import com.dolphin.report_book.entity.User;
import com.dolphin.report_book.entity.dto.ResponseResult;

import java.util.List;

public interface MeetingService {
    boolean insert(Meeting meeting);

    boolean deleteById(Integer id);

    Meeting getById(Integer id);

    Meeting getByReportId(Integer id);

    List<Meeting> listMeetings();

    List<Meeting> listAppointmentEndMeetings(Integer collegeId);

    List<Meeting> listFinishMeetings(Integer collegeId);

    List<Meeting> listMyFinishMeetings(Integer id);

    List<Meeting> searchMeetings(String year, String semester, Integer collegeId);

    List<Meeting> listMeetings(Meeting meeting);

    int countMeetings(Meeting meeting);

    boolean update(Meeting meeting);

    List<Meeting> listAppointingMeeting(Integer studentId);

    List<Meeting> listAppointingFinishMeeting(Integer studentId);

    List<String> getAllYears(Integer collegeId);

    ResponseResult arrangeMeeting(Integer id, String hostNo, String reportTime, String reportAddress, String appointEnd, Integer capacity);

    ResponseResult updateMeeting(Integer meetingId, String hostNo, String reportTime, String reportAddress, String appointEnd, Integer capacity);

    ResponseResult applyMeeting(Integer id, User loginUser);

}
