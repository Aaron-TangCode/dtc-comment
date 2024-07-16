package com.datoucai.service;

import com.datoucai.service.dto.SensitiveWordDto;

/**
 * 敏感词
 * @Date：2024/7/16 14:44
 */
public interface ISensitiveService {

    int insert(SensitiveWordDto sensitiveWordDto);
}
