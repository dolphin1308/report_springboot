package com.dolphin.report_book.mapper;
import com.dolphin.report_book.entity.Student;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StudentMapper {
    /**
     * 添加Student
     */
    int insert(Student student);

    /**
     * 删除Student
     */
    int deleteById(Integer id);

    /**
     * 查询单条数据
     */
    Student getById(Integer id);

    /**
     * 查询单条数据
     */
    Student getByNo(String no);

    /**
     * 查询全部数据
     * 分页使用MyBatis的插件实现
     */
    List<Student> listStudents();

    /**
     * 实体作为筛选条件查询数据
     */
    List<Student> listStudents(Student student);

    /**
     * 实体作为筛选条件获取结果数量
     */
    int countStudents(Student student);

    /**
     * 修改Student, 根据 student 的主键修改数据
     */
    int update(Student student);
}
