package com.datoucai.param;

import lombok.Data;

import java.util.List;

/**
 * 评论详情信息
 */
@Data
public class CommentInfoEntity {
    /**
     * 用户id
     */
    private String userId;

    /**
     * 评论id
     */
    private String commentId;

    /**
     * 模块
     * 0：社区模块
     * 1：游戏模块
     * 2：短视频模块"
     */
    private Integer module;
    /**
     * 资源id
     */
    private String resourceId;
    /**
     * 内容
     */
    private String content;
    /**
     * 评论时间
     * 格式：yyyy-MM-dd HH:mm:ss
     */
    private String contentTime;
    /**
     * 点赞数
     */
    private Integer starNum;
    /**
     * 头像
     */
    private String avatar;
    /**
     * 用户名
     */
    private String username;
    /**
     * 回复数
     */
    private Integer replyNum;
    /**
     * 状态
     * 1:置顶。
     * 0：取消置顶"
     */
    private Integer status;
    /**
     * 子回复列表
     */
    private List<ReplyInfoEntity> list;
}
