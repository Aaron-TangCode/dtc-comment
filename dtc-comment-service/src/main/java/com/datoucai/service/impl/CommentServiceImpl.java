package com.datoucai.service.impl;

import com.datoucai.dto.CommentDetailInfoDto;
import com.datoucai.dto.CommentInfoDto;
import com.datoucai.dto.CommentResultInfoDto;
import com.datoucai.entity.CommentEntity;
import com.datoucai.entity.CommentParam;
import com.datoucai.mapper.CommentMapper;
import com.datoucai.service.ICommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 评论service
 */
@Service
@Slf4j
public class CommentServiceImpl implements ICommentService {
    @Autowired
    private CommentMapper commentMapper;

    @Override
    public int addComment(CommentInfoDto dto) {
        CommentEntity commentEntity = new CommentEntity();
        BeanUtils.copyProperties(dto,commentEntity);
        int count = commentMapper.addComment(commentEntity);
        return count;
    }

    @Override
    public int deleteComment(CommentInfoDto dto) {
        log.info("删除评论-入参:{}",dto);
        // todo 缺少参数信息校验
        int count = commentMapper.deleteCommentById(dto.getId());
        return count;
    }

    @Override
    public CommentResultInfoDto queryCommentByParam(CommentInfoDto dto) {
        log.info("查询评论-queryCommentByParam-入参:{}",dto);
        CommentResultInfoDto resultInfoDto = new CommentResultInfoDto();
        // 步骤1：检查参数
        checkParam(dto);
        // 步骤2：构建查询条件
        CommentParam queryParam = buildQueryCommentParam(dto);
        // 步骤3：查询评论总数
        int total = commentMapper.countCommentByParam(queryParam);
        resultInfoDto.setTotal(Long.valueOf(total+""));
        if(total<=0){
            return resultInfoDto;
        }
        // 步骤4：查询评论列表
        List<CommentEntity> commentEntities = commentMapper.queryCommentByParam(queryParam);

        // 步骤5：组装结果集
        List<CommentDetailInfoDto> list = buildResultList(commentEntities);
        resultInfoDto.setList(list);
        log.info("查询评论-queryCommentByParam-出参:{}",resultInfoDto);
        return resultInfoDto;
    }

    /**
     * 构建结果集
     * @param commentEntities
     * @return
     */
    private List<CommentDetailInfoDto> buildResultList(List<CommentEntity> commentEntities) {
        if(CollectionUtils.isEmpty(commentEntities)){
            return new ArrayList<>();
        }
        List<CommentDetailInfoDto> resultInfoDtos = new ArrayList<>();

        for (int i = 0; i < commentEntities.size(); i++) {
            CommentEntity commentEntity = commentEntities.get(i);
            if(commentEntity==null){
                continue;
            }
            CommentDetailInfoDto target = new CommentDetailInfoDto();
            BeanUtils.copyProperties(commentEntity,target);
            resultInfoDtos.add(target);
        }
        return resultInfoDtos;
    }

    /**
     * 构建查询评论的条件
     * @param dto
     * @return
     */
    private CommentParam buildQueryCommentParam(CommentInfoDto dto) {
        CommentParam commentParam = new CommentParam();
        commentParam.setModule(dto.getModule());
        commentParam.setResourceId(dto.getResourceId());
        commentParam.setLimit(dto.getPageSize());
        commentParam.setOffset(buildOffset(dto.getPageNum(),dto.getPageSize()));
        if(dto.getOrder()==null){
            commentParam.setOrderBy("create_time");
            commentParam.setOrderDirection("desc");
        }else{
            if(dto.getOrder()==2){
                commentParam.setOrderBy("star_num");
                commentParam.setOrderDirection("desc");
            }else{
                commentParam.setOrderBy("create_time");
                commentParam.setOrderDirection("asc");
            }
        }
        return commentParam;
    }

    /**
     * 计算偏移量
     * @param pageNum
     * @param pageSize
     * @return
     */
    private Integer buildOffset(Integer pageNum, Integer pageSize) {
        Integer offset = (pageNum-1)* pageSize;
        return Math.max(0,offset);
    }

    private void checkParam(CommentInfoDto dto) {
        Assert.isTrue(dto!=null,"参数不能为空");
        Assert.isTrue(dto.getModule()!=null,"模块不能为空");
        Assert.isTrue(dto.getResourceId()!=null,"资源id不能为空");
        // 补全分页信息
        if(dto.getPageNum()==null || dto.getPageSize()==null){
            dto.setPageNum(1);
            dto.setPageSize(10);
        }
    }
}
