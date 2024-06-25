package com.datoucai.service.impl;

import com.alibaba.fastjson.JSON;
import com.datoucai.entity.CommentEntity;
import com.datoucai.mapper.CommentMapper;
import com.datoucai.mapper.UserMapper;
import com.datoucai.param.CommentParam;
import com.datoucai.service.ICommentService;
import com.datoucai.service.IUserService;
import com.datoucai.service.dto.CommentDetailDto;
import com.datoucai.service.dto.CommentInfoDto;
import com.datoucai.service.dto.CommentInfoResultDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 评论服务
 */
@Service
@Slf4j
public class CommentServiceImpl implements ICommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Override
    public int addComment(CommentInfoDto dto) {
        try {
            log.info("增加评论-addComment-入参:{}", JSON.toJSONString(dto));
            CommentEntity target = new CommentEntity();
            BeanUtils.copyProperties(dto,target);
            int count = commentMapper.insertComment(target);
            log.info("增加评论-addComment-出参:{}", count);
            return count;
        }catch (Exception e){
            log.error("增加评论-addComment-异常:", e);
            return -1;
        }
    }

    @Override
    public int deleteComment(CommentInfoDto dto) {
        try {
            log.info("删除评论-deleteComment-入参:{}", JSON.toJSONString(dto));
            CommentEntity target = new CommentEntity();
            BeanUtils.copyProperties(dto,target);
            int count = commentMapper.deleteCommentById(target.getId());
            log.info("删除评论-deleteComment-出参:{}", count);
            return count;
        }catch (Exception e){
            log.error("删除评论-deleteComment-异常:", e);
            return -1;
        }
    }

    @Override
    public CommentInfoResultDto queryCommentByParam(CommentInfoDto dto) {
        try {
            log.info("查询评论-queryCommentByParam-入参:{}", JSON.toJSONString(dto));
            CommentParam target = new CommentParam();
            BeanUtils.copyProperties(dto,target);

            CommentInfoResultDto resultDto = new CommentInfoResultDto();
            int total = commentMapper.countCommentByParam(target);
            resultDto.setTotal(Long.valueOf(total+""));
            if(total<=0){
                return resultDto;
            }
            List<CommentEntity> list = commentMapper.queryCommentByParam(target);
            log.info("查询评论-queryCommentByParam-数据库-出参:{}", JSON.toJSONString(list));
            if(CollectionUtils.isEmpty(list)){
                return resultDto;
            }
            List<CommentDetailDto> resultList = buildResultList(list);
            resultDto.setList(resultList);
            log.info("查询评论-queryCommentByParam-接口-出参:{}", JSON.toJSONString(resultList));
            return resultDto;
        }catch (Exception e){
            log.error("查询评论-queryCommentByParam-异常:", e);
            return new CommentInfoResultDto();
        }
    }

    /**
     * 构建评论信息结果集
     * @param list
     * @return
     */
    private List<CommentDetailDto> buildResultList(List<CommentEntity> list) {
        if(CollectionUtils.isEmpty(list)){
            return new ArrayList<>();
        }
        List<CommentDetailDto> detailDtoList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            CommentEntity commentEntity = list.get(i);
            CommentDetailDto commentDetailDto = new CommentDetailDto();
            BeanUtils.copyProperties(commentEntity,commentDetailDto);
            detailDtoList.add(commentDetailDto);
        }
        return detailDtoList;
    }
}
