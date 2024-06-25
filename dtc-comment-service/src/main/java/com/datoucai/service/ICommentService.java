package com.datoucai.service;

import com.datoucai.service.dto.CommentDetailDto;
import com.datoucai.service.dto.CommentInfoDto;
import com.datoucai.service.dto.CommentInfoResultDto;

import java.util.List;

/**
 *  评论服务
 */
public interface ICommentService {

    /**
     * 增加评论
     * @return
     */
    int addComment(CommentInfoDto dto);

    /**
     * 删除评论
     * @return
     */
    int deleteComment(CommentInfoDto dto);

    /**
     * 查询评论
     * @return
     */
    CommentInfoResultDto queryCommentByParam(CommentInfoDto dto);
}
