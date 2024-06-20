package com.datoucai.entity;


import lombok.Data;

import java.util.Date;

@Data
public class UserEntity {
    private Long id;
    private String name;
    /**
     * 地址
     */
    private String address;
    /**
     * 邮件
     */
    private String email;

    private Date createTime;
    private Date updateTime;
}
