package com.dolphin.report_book.mapper;

import com.dolphin.report_book.entity.Meeting;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MeetingMapper {
    /**
     * 添加Meeting
     */
    int insert(Meeting meeting);

    /**
     * 删除Meeting
     */
    int deleteById(Integer id);

    /**
     * 查询单条数据
     */
    Meeting getById(Integer id);

    /**
     * 查询单条数据
     */
    Meeting getByReportId(Integer id);

    /**
     * 查询全部数据
     * 分页使用MyBatis的插件实现
     */
    List<Meeting> listMeetings();

    /**
     * 查询预约结束且会议时间未达到的会议
     * 分页使用MyBatis的插件实现
     */
    List<Meeting> listAppointmentEndMeetings(Integer collegeId);

    /**
     * 查询已完成的会议
     * 分页使用MyBatis的插件实现
     */
    List<Meeting> listFinishMeetings(Integer collegeId);

    /**
     * 查询我已完成的会议
     * 分页使用MyBatis的插件实现
     */
    List<Meeting> listMyFinishMeetings(Integer id);

    /**
     * 通过学年和学期搜索指定学院的会议
     */
    List<Meeting> searchMeetings(@Param("year") String year, @Param("semester") String semester, @Param("collegeId") Integer collegeId);

    /**
     * 实体作为筛选条件查询数据
     */
    List<Meeting> listMeetings(Meeting meeting);

    /**
     * 实体作为筛选条件获取结果数量
     */
    int countMeetings(Meeting meeting);

    /**
     * 修改Meeting, 根据 meeting 的主键修改数据
     */
    int update(Meeting meeting);

    /**
     * 获取指定学生预约的全部会议
     */
    List<Meeting> listAppointingMeeting(Integer studentId);

    /**
     * 获取指定学生的参会记录
     */
    List<Meeting> listAppointingFinishMeeting(Integer studentId);

    /**
     * 获取指定学院的开展过报告会议的学年
     */
    List<String> getAllYears(Integer collegeId);
}
