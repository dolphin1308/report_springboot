package com.dolphin.report_book.entity;
import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
import com.dolphin.report_book.constant.Photo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ExcelTarget("Student")
public class Student implements Serializable {
    private static final long serialVersionUID = 972593633686891663L;

    /**
     * 主键ID
     */
    private Integer id;

    /**
     * 学号【201724114111】
     */
    @Excel(name = "学号", width = 20)
    private String no;

    /**
     * 姓名
     */
    @Excel(name = "姓名")
    private String name;

    /**
     * 登录密码
     */
    private String password;

    /**
     * 邮箱
     */
    @Excel(name = "邮箱", width = 20)
    private String email;

    /**
     * 手机号码
     */
    @Excel(name = "手机号码", width = 20)
    private String phone;

    /**
     * 性别
     */
    @Excel(name = "性别")
    private String sex;

    /**
     * 出生日期【1999-09-09】
     */
    @Excel(name = "出生日期", format = "yyyy年MM月dd日", width = 20)
    private Date birthday;

    /**
     * 出生日期字符串
     */
    private String birthdayStr;

    /**
     * 年级【2017】
     */
    @Excel(name = "年级")
    private Integer grade;

    /**
     * 专业
     */
    @Excel(name = "专业", width = 30)
    private String major;

    /**
     * 班级信息【1班】
     */
    @Excel(name = "班级")
    private String classInfo;

    /**
     * 学院ID
     */
    private Integer collegeId;

    /**
     * 院系
     */
    @Excel(name = "院系", width = 30)
    private String collegeName;

    /**
     * 院系
     */
    private College college;

    /**
     * 头像
     */
    private String img = Photo.student;
}
