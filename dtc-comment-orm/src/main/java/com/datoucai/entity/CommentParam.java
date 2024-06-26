package com.datoucai.entity;

import lombok.Data;

import java.util.Date;

@Data
public class CommentParam {
    /**
     * id
     */
    private Long id;
    /**
     * 用户id
     */
    private Long userId;
    /**
     * 模块
     */
    private Integer module;
    /**
     * 资源id
     */
    private Long resourceId;
    /**
     * 评论内容
     */
    private String content;
    /**
     * 1：置顶
     * 0：普通
     */
    private Integer status;
    /**
     * 评分
     */
    private Integer score;
    /**
     * 点赞数
     */
    private Integer starNum;
    /**
     * 是否删除
     * 1：删除
     * 0: 正常
     */
    private Integer isDelete;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 修改时间
     */
    private Date updateTime;

    private Integer limit;

    private Integer offset;
    /**
     * 排序
     */
    private String orderBy;
    /**
     * 正序 asc ；逆序 desc
     */
    private String orderDirection;

}
