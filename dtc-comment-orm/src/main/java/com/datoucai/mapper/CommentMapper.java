package com.datoucai.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CommentMapper {

    int addComment();
}
