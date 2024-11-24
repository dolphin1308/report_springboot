package com.dolphin.report_book.mapper;
import com.dolphin.report_book.entity.Department;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DepartmentMapper {

    /**
     * 添加Department
     */
    int insert(Department department);

    /**
     * 删除Department
     */
    int deleteById(Integer id);

    /**
     * 查询单条数据
     */
    Department getById(Integer id);

    /**
     * 查询单条数据
     */
    Department getByNo(String no);

    /**
     * 查询全部数据
     * 分页使用MyBatis的插件实现
     */
    List<Department> listDepartments();

    /**
     * 实体作为筛选条件查询数据
     */
    List<Department> listDepartments(Department department);

    /**
     * 实体作为筛选条件获取结果数量
     */
    int countDepartments(Department department);

    /**
     * 修改Department, 根据 department 的主键修改数据
     */
    int update(Department department);

}