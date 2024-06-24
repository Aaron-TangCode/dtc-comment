package com.datoucai.param;

import lombok.Data;

import java.util.List;

/**
 * 分页评论的结果
 */
@Data
public class CommentResultParam {
    /**
     * 分页总数
     */
    private Long total;
    /**
     * 分页list
     */
    private List<CommentInfoEntity> list;
}
