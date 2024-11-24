package com.dolphin.report_book.constant;

public class ReportConstant {
    /**
     * 未通过教务部门审核
     */
    public static final Integer NO_PASS_FROM_DEPT = -2;

    /**
     * 未通过院长审核
     */
    public static final Integer NO_PASS_FROM_LEADER = -1;

    /**
     * 等待审核
     */
    public static final Integer WAIT = 0;

    /**
     * 通过院长审核
     */
    public static final Integer PASS_FROM_LEADER = 1;

    /**
     * 通过教务部门审核
     */
    public static final Integer PASS_FROM_DEPT = 2;

    /**
     * 正在预约会议
     */
    public static final Integer APPOINTMENT = 3;

    /**
     * 已完成
     */
    public static final Integer FINISHED = 4;

    /**
     * 回收站
     */
    public static final Integer TRASH = 5;
}
