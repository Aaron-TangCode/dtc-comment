package com.datoucai.service.dto;

import lombok.Data;

import java.util.Date;

/**
 * @Author <a href="mailto:yebing.thl@alibaba-inc.com">叶冰</a>
 * @Date：2024/6/25 17:25
 */
@Data
public class CommentInfoDto {
    private Long id;

    private Long userId;

    private Integer module;

    private Integer status;

    private Long resourceId;

    private String content;

    private Integer starNum;

    private Integer score;

    private Integer isDelete;

    private Date createTime;

    private Date updateTime;
}
