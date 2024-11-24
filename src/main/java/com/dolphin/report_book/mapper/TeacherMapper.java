package com.dolphin.report_book.mapper;
import com.dolphin.report_book.entity.Teacher;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TeacherMapper {
    /**
     * 添加Teacher
     */
    int insert(Teacher teacher);

    /**
     * 删除Teacher
     */
    int deleteById(Integer id);

    /**
     * 查询单条数据
     */
    Teacher getById(Integer id);

    /**
     * 查询单条数据
     */
    Teacher getByNo(String no);

    /**
     * 查询全部数据
     * 分页使用MyBatis的插件实现
     */
    List<Teacher> listTeachers();

    /**
     * 实体作为筛选条件查询数据
     */
    List<Teacher> listTeachers(Teacher teacher);

    /**
     * 实体作为筛选条件获取结果数量
     */
    int countTeachers(Teacher teacher);

    /**
     * 修改Teacher, 根据 teacher 的主键修改数据
     */
    int update(Teacher teacher);
}
