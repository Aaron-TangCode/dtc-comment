package com.datoucai.param;

import lombok.Data;

import java.util.Date;

/**
 * 评论实体类-查询
 */
@Data
public class CommentParam {

    private Long id;

    private Long userId;

    private Integer module;

    private Integer status;

    private String resourceId;

    private String content;

    private Integer starNum;

    private Integer score;

    private Integer isDelete;

    private Date createTime;

    private Date updateTime;

    private Integer order;

    private Integer limit;

    private Integer offset;

    private String orderBy;

    private String orderDirection;

}
