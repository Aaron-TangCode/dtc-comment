package com.datoucai.param;

import lombok.Data;

/**
 * 增加评论
 */
@Data
public class QueryCommentRequestParam {
    /**
     * 用户id
     */
    private String userId;

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
    /**
     * 排序方式
     * 1：最新
     * 2：最热
     * 3：最早
     */
    private Integer order;
    /**
     * 子回复数量
     */
    private Integer replyNum;
    /**
     * 页码
     */
    private Integer pageNum;
    /**
     * 页大小
     */
    private Integer pageSize;
}
