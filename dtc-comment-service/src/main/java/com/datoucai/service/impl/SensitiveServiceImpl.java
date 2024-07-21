package com.datoucai.service.impl;

import com.datoucai.dao.TbSensitiveWordsDao;
import com.datoucai.dto.SensitiveWordDto;
import com.datoucai.entity.TbSensitiveWords;
import com.datoucai.entity.TbSensitiveWordsExample;
import com.datoucai.service.ISensitiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class SensitiveServiceImpl implements ISensitiveService {
    @Autowired
    private TbSensitiveWordsDao tbSensitiveWordsDao;

    @Override
    public int insert(SensitiveWordDto sensitiveWordDto) {
        TbSensitiveWords tbSensitiveWords = new TbSensitiveWords();
        tbSensitiveWords.setWord(sensitiveWordDto.getWord());
        tbSensitiveWords.setCategory(sensitiveWordDto.getCategory());
        // 添加全部字段
//        return tbSensitiveWordsDao.insert(tbSensitiveWords);
        // 添加选择字段，其他字段默认值
        return tbSensitiveWordsDao.insertSelective(tbSensitiveWords);
    }

    @Override
    public List<SensitiveWordDto> queryByParam(TbSensitiveWordsExample tbSensitiveWordsExample) {
        List<TbSensitiveWords> tbSensitiveWords = tbSensitiveWordsDao.selectByExample(tbSensitiveWordsExample);
        if(CollectionUtils.isEmpty(tbSensitiveWords)){
            return new ArrayList<>();
        }
        List<SensitiveWordDto> resultList = buildSensitiveWordDtos(tbSensitiveWords);

        return resultList;
    }

    /**
     * 结果集转换
     * @param tbSensitiveWords
     * @return
     */
    private List<SensitiveWordDto> buildSensitiveWordDtos(List<TbSensitiveWords> tbSensitiveWords) {
        List<SensitiveWordDto> resultList = new ArrayList<>();
        for (int i = 0; i < tbSensitiveWords.size(); i++) {
            TbSensitiveWords tbSensitiveWord = tbSensitiveWords.get(i);
            if(tbSensitiveWord==null){
                continue;
            }
            SensitiveWordDto wordDto = new SensitiveWordDto();
            wordDto.setWord(tbSensitiveWord.getWord());
            wordDto.setCategory(tbSensitiveWord.getCategory());
            resultList.add(wordDto);
        }
        return resultList;
    }
}
