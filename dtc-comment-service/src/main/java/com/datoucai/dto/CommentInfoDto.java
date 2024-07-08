package com.datoucai.dto;

import lombok.Data;

import java.util.Date;

/**
 * 评论的dto
 */
@Data
public class CommentInfoDto {

    private Long id;

    private Long userId;

    private Integer module;

    private Long resourceId;

    private String content;

    private Integer status;
    /**
     * 评分
     */
    private Integer score;

    /**
     * 排序方式
     * 1：最新
     * 2：最热
     * 3：最早
     */
    private Integer order;

    private Integer starNum;

    private Integer isDelete;

    private Date createTime;

    private Date updateTime;

    private Integer pageNum;

    private Integer pageSize;

}
