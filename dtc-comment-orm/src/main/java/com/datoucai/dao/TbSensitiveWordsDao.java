package com.datoucai.dao;


import java.util.List;

import com.datoucai.entity.TbSensitiveWords;
import com.datoucai.entity.TbSensitiveWordsExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface TbSensitiveWordsDao {
    long countByExample(TbSensitiveWordsExample example);

    int deleteByExample(TbSensitiveWordsExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TbSensitiveWords record);

    int insertSelective(TbSensitiveWords record);

    List<TbSensitiveWords> selectByExample(TbSensitiveWordsExample example);

    TbSensitiveWords selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TbSensitiveWords record, @Param("example") TbSensitiveWordsExample example);

    int updateByExample(@Param("record") TbSensitiveWords record, @Param("example") TbSensitiveWordsExample example);

    int updateByPrimaryKeySelective(TbSensitiveWords record);

    int updateByPrimaryKey(TbSensitiveWords record);
}