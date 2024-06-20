package com.datoucai.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    /**
     * 计算评论条数
     * @return
     */
    int countUser();
}
