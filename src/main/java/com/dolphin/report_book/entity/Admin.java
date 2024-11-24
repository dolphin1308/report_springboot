package com.dolphin.report_book.entity;

import com.dolphin.report_book.constant.Photo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Admin implements Serializable {
    private static final long serialVersionUID = 603001270166502673L;

    /**
     * 主键ID
     */
    private Integer id;

    /**
     * 管理员编号
     */
    private String no;

    /**
     * 登录密码
     */
    private String password;

    /**
     * 头像
     */
    private String img = Photo.admin;
}
