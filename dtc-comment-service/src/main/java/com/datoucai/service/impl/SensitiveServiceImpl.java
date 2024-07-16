package com.datoucai.service.impl;

import com.alibaba.fastjson.JSON;
import com.datoucai.dao.TbSensitiveWordsDao;
import com.datoucai.entity.TbSensitiveWords;
import com.datoucai.entity.TbSensitiveWordsExample;
import com.datoucai.service.ISensitiveService;
import com.datoucai.service.dto.SensitiveWordDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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

    @Override
    public List<SensitiveWordDto> queryByParam(TbSensitiveWordsExample sensitiveWordsExample) {


        List<TbSensitiveWords> tbSensitiveWords = tbSensitiveWordsDao.selectByExample(sensitiveWordsExample);
        if(CollectionUtils.isEmpty(tbSensitiveWords)){
            return new ArrayList<>();
        }
        List<SensitiveWordDto> resultList = buildTbSensitiveWords(tbSensitiveWords);
        log.info("查询敏感词-列表-结果:{}", JSON.toJSONString(resultList));
        return resultList;
    }

    private List<SensitiveWordDto> buildTbSensitiveWords(List<TbSensitiveWords> tbSensitiveWords) {
        if(CollectionUtils.isEmpty(tbSensitiveWords)){
            return new ArrayList<>();
        }
        List<SensitiveWordDto> resultList = new ArrayList<>();
        for (int i = 0; i < tbSensitiveWords.size(); i++) {
            TbSensitiveWords source = tbSensitiveWords.get(i);
            if(source==null){
                continue;
            }
            SensitiveWordDto target = new SensitiveWordDto();
            target.setWord(source.getWord());
            target.setCategory(source.getCategory());
            resultList.add(target);

        }
        return resultList;
    }
}
