package com.datoucai.mapper;

import com.datoucai.entity.CommentEntity;
import com.datoucai.param.CommentParam;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommentMapper {
    /**
     * 插入评论-单个
     * @param commentEntity
     * @return
     */
    int insertComment(CommentEntity commentEntity);

    /**
     * 根据评论id删除评论
     * @param id
     * @return
     */
    int deleteCommentById(Long id);

    /**
     * 根据条件查询评论
     * @param param
     * @return
     */
    List<CommentEntity> queryCommentByParam(CommentParam param);

    /**
     * 根据条件查询评论总数
     * @param param
     * @return
     */
    int countCommentByParam(CommentParam param);

}
