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
import com.datoucai.service.dto.QueryCommentInfoDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
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
    public CommentInfoResultDto queryCommentByParam(QueryCommentInfoDto dto) {
        try {
            log.info("查询评论-queryCommentByParam-入参:{}", JSON.toJSONString(dto));
            checkParam(dto);
            CommentInfoResultDto resultDto = new CommentInfoResultDto();
            CommentParam queryParam = buildParam(dto);
            log.info("查询评论-查数据库条件-入参:{}", JSON.toJSONString(queryParam));
            int total = commentMapper.countCommentByParam(queryParam);
            resultDto.setTotal(Long.valueOf(total+""));
            if(total<=0){
                return resultDto;
            }
            List<CommentEntity> list = commentMapper.queryCommentByParam(queryParam);
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
           throw e;
        }
    }

    /**
     * 参数校验
     * @param dto
     */
    private void checkParam(QueryCommentInfoDto dto) {
        Assert.isTrue(dto!=null,"参数不能为空");
        Assert.isTrue(dto.getModule()!=null,"模块不能为空");
        Assert.isTrue(dto.getResourceId()!=null,"资源Id不能为空");
        if(dto.getPageNum()==null || dto.getPageSize()==null){
            dto.setPageNum(1);
            dto.setPageSize(10);
        }
    }

    /**
     * 构建查询条件-查询评论
     * @param dto
     * @return
     */
    private CommentParam buildParam(QueryCommentInfoDto dto) {
        CommentParam commentParam = new CommentParam();
        if(dto==null){
            return commentParam;
        }
        commentParam.setModule(dto.getModule());
        commentParam.setResourceId(dto.getResourceId());
        commentParam.setOffset(buildOffset(dto.getPageNum(),dto.getPageSize()));
        commentParam.setLimit(dto.getPageSize());
        commentParam.setIsDelete(0);
        if(dto.getOrder()==null || dto.getOrder()==1){
            // 默认最新
            commentParam.setOrderBy("create_time");
            commentParam.setOrderDirection("desc");
        }else{
            /**
             * 排序方式
             * 1：最新
             * 2：最热
             * 3：最早
             */
            if(dto.getOrder()==2){
                // 最热
                commentParam.setOrderBy("star_num");
                commentParam.setOrderDirection("desc");
            }else{
                // 最新
                commentParam.setOrderBy("create_time");
                commentParam.setOrderDirection("asc");
            }
        }

        if(dto.getScore()!=null){
            commentParam.setScore(dto.getScore());
        }

        return commentParam;
    }

    private Integer buildOffset(Integer pageNum, Integer pageSize) {
        if(pageNum==null || pageSize==null){

        }
        Integer offset = (pageNum-1) * pageSize;
        return Math.max(offset,0);
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
