package com.datoucai.service.impl;

import com.datoucai.dao.TbSensitiveWordsDao;
import com.datoucai.entity.TbSensitiveWords;
import com.datoucai.service.ISensitiveService;
import com.datoucai.service.dto.SensitiveWordDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SensitiveServiceImpl implements ISensitiveService {

    @Autowired
    private TbSensitiveWordsDao tbSensitiveWordsDao;

    @Override
    public int insert(SensitiveWordDto sensitiveWordDto) {
        TbSensitiveWords tbSensitiveWords = new TbSensitiveWords();
        tbSensitiveWords.setWord(sensitiveWordDto.getWord());
        tbSensitiveWords.setCategory(sensitiveWordDto.getCategory());
        return tbSensitiveWordsDao.insertSelective(tbSensitiveWords);
    }
}
