package com.datoucai.service;

import com.datoucai.dto.SensitiveWordDto;
import com.datoucai.entity.TbSensitiveWordsExample;

import java.util.List;

/**
 * 敏感词过滤-service
 */
public interface ISensitiveService {

    int insert(SensitiveWordDto sensitiveWordDto);


    List<SensitiveWordDto> queryByParam(TbSensitiveWordsExample tbSensitiveWordsExample);

}
