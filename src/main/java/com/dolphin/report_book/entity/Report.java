package com.dolphin.report_book.entity;
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
public class Report implements Serializable {

    private static final long serialVersionUID = -82018923854788072L;

    /**
     * ID
     */
    private Integer id;

    /**
     * 报告名称
     */
    private String title;

    /**
     * 报告人教师编号
     */
    private String reporterNo;

    /**
     * 报告人简介
     */
    private String reporterInfo;

    /**
     * 报告简介
     */
    private String info;

    /**
     * 附件文件存放路径
     */
    private String attachment;

    /**
     * 发布时间
     */
    private Date publishTime;

    /**
     * 【-2：教务部门审核未通过】【-1：院长审核未通过】【0：等待审核】【1：院长审核通过等待教务部门审核】【2：教务部门审核通过】【3：预约会议】【4：会议完成】【5：回收站】
     */
    private Integer status;

    /**
     * 院长审核不通过的指导意见
     */
    private String checkInfo1;

    /**
     * 教务部门审核不通过的指导意见
     */
    private String checkInfo2;

    /**
     * 报告文件
     */
    private String reportFile;

}
