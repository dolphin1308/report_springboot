package com.dolphin.report_book.mapper;
import com.dolphin.report_book.entity.Appointment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AppointmentMapper {
    /**
     * 添加Appointment
     */
    int insert(Appointment appointment);

    /**
     * 删除Appointment
     */
    int deleteById(Integer id);

    /**
     * 查询单条数据
     */
    Appointment getById(Integer id);

    /**
     * 查询全部数据
     * 分页使用MyBatis的插件实现
     */
    List<Appointment> listAppointments();

    /**
     * 实体作为筛选条件查询数据
     */
    List<Appointment> listAppointments(Appointment appointment);

    /**
     * 实体作为筛选条件获取结果数量
     */
    int countAppointments(Appointment appointment);

    /**
     * 修改Appointment, 根据 appointment 的主键修改数据
     */
    int update(Appointment appointment);

    /**
     * 修改数据，哪个属性不为空就修改哪个属性
     */
    boolean updatePresent(Appointment appointment);
}
