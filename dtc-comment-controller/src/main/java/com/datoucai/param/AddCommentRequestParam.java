package com.datoucai.param;

import lombok.Data;

/**
 * 增加评论
 */
@Data
public class AddCommentRequestParam {
    /**
     * 用户id
     */
    private String userId;
    /**
     * 评论内容
     */
    private String content;
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
     * 评分
     */
    private Integer score;
}
