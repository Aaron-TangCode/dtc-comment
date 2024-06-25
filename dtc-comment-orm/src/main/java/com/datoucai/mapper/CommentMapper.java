package com.datoucai.mapper;

import com.datoucai.entity.CommentEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CommentMapper {

    /**
     * 插入评论
     * @param entity
     * @return
     */
    int addComment(CommentEntity entity);
}
