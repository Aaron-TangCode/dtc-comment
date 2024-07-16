package com.datoucai.service;

import com.datoucai.entity.TbSensitiveWordsExample;
import com.datoucai.service.dto.SensitiveWordDto;

import java.util.List;

/**
 * 敏感词
 * @Date：2024/7/16 14:44
 */
public interface ISensitiveService {

    int insert(SensitiveWordDto sensitiveWordDto);

    List<SensitiveWordDto> queryByParam(TbSensitiveWordsExample sensitiveWordsExample);
}
