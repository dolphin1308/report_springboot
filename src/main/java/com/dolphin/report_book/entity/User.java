package com.dolphin.report_book.entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    /**
     * ID
     */
    private Integer id;
    /**
     * 编号
     */
    private String no;

    /**
     * 名称
     */
    private String name;

    /**
     * 角色
     */
    private String role;

    /**
     * 头像
     */
    private String img;

    /**
     * 所属学院
     */
    private College college;

    /**
     * 是否为学院领导
     */
    private int isLeader;
}
