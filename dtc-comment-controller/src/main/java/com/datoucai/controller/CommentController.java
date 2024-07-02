package com.datoucai.controller;

import com.alibaba.fastjson.JSON;
import com.datoucai.dto.CommentDetailInfoDto;
import com.datoucai.dto.CommentInfoDto;
import com.datoucai.dto.CommentResultInfoDto;
import com.datoucai.param.AddCommentRequestParam;
import com.datoucai.param.BaseResult;
import com.datoucai.param.CommentInfoEntity;
import com.datoucai.param.CommentResultParam;
import com.datoucai.param.DelCommentRequestParam;
import com.datoucai.param.QueryCommentRequestParam;
import com.datoucai.service.ICommentService;
import com.datoucai.utils.BaseResultUtils;
import com.datoucai.utils.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/comment")
@Slf4j
public class CommentController {

    @Autowired
    private ICommentService commentService;

    /**
     * 增加评论
     * @param param
     * @return
     */
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public BaseResult<Boolean> addComment(@RequestBody AddCommentRequestParam param){
        try {
            log.info("增加评论-controller层-addComment-入参:{}", JSON.toJSONString(param));
            checkParam(param);
            CommentInfoDto dto = buildCommentInfoDto(param);
            int count = commentService.addComment(dto);
            log.info("增加评论-controller层-addComment-出参:{}", count);
            return BaseResultUtils.generateSuccess(count>0);
        }catch (Exception e){
            log.error("增加评论-controller层-addComment-异常:", e);
            return BaseResultUtils.generateFail("增加评论异常:"+e.getMessage());
        }
    }

    private void checkParam(AddCommentRequestParam param) {
        Assert.isTrue(param!=null,"入参不能为空");
        Assert.isTrue(StringUtils.isNotBlank(param.getUserId()),"用户id不能为空");
        Assert.isTrue(StringUtils.isNotBlank(param.getResourceId()),"资源id不能为空");
        Assert.isTrue(param.getModule()!=null,"模块不能为空");
        Assert.isTrue(StringUtils.isNotBlank(param.getContent()),"内容不能为空");
    }

    private static CommentInfoDto buildCommentInfoDto(AddCommentRequestParam param) {
        CommentInfoDto dto = new CommentInfoDto();
        dto.setUserId(Long.valueOf(param.getUserId()));
        dto.setModule(param.getModule());
        dto.setResourceId(Long.valueOf(param.getResourceId()));
        dto.setContent(param.getContent());
        dto.setStatus(0);
        dto.setScore(param.getScore());
        dto.setStarNum(0);
        dto.setIsDelete(0);
        dto.setCreateTime(new Date());
        dto.setUpdateTime(new Date());
        return dto;
    }

    /**
     * 删除评论
     * @param param
     * @return
     */
    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    public BaseResult<Boolean> deleteComment(@RequestBody DelCommentRequestParam param){
        try {
            log.info("删除评论-controller层-deleteComment-入参:{}", JSON.toJSONString(param));
            CommentInfoDto dto = getCommentInfoDto(param);
            int count = commentService.deleteComment(dto);
            log.info("删除评论-controller层-deleteComment-出参:{}", count);
            return BaseResultUtils.generateSuccess(count>0);
        }catch (Exception e){
            log.error("删除评论-controller层-deleteComment-异常:", e);
            return BaseResultUtils.generateFail("删除评论异常");
        }
    }

    private static CommentInfoDto getCommentInfoDto(DelCommentRequestParam param) {
        CommentInfoDto dto = new CommentInfoDto();
        dto.setId(Long.valueOf(param.getCommentId()));
        dto.setUserId(Long.valueOf(param.getUserId()));
        dto.setModule(param.getModule());
        dto.setResourceId(Long.valueOf(param.getResourceId()));
        dto.setUpdateTime(new Date());
        return dto;
    }

    /**
     * 查询评论
     * @param param
     * @return
     */
    @RequestMapping(value = "/query",method = RequestMethod.GET)
    public BaseResult<CommentResultParam> queryComment(QueryCommentRequestParam param){
        try {
            log.info("查询评论-controller层-queryComment-入参:{}", JSON.toJSONString(param));
            CommentInfoDto commentInfoDto = buildCommentInfoDto(param);
            CommentResultInfoDto resultInfoDto = commentService.queryCommentByParam(commentInfoDto);
            CommentResultParam resultParam = buildCommentResultParam(resultInfoDto);
            log.info("查询评论-controller层-queryComment-出参:{}", JSON.toJSONString(resultParam));
            return BaseResultUtils.generateSuccess(resultParam);
        }catch (Exception e){
            log.error("查询评论-controller层-queryComment-异常:", e);
            return BaseResultUtils.generateFail("查询评论异常");
        }
    }

    private CommentResultParam buildCommentResultParam(CommentResultInfoDto resultInfoDto) {
        if(resultInfoDto==null){
            return null;
        }
        CommentResultParam resultParam = new CommentResultParam();
        resultParam.setTotal(resultInfoDto.getTotal());
        resultParam.setList(buildCommentInfoEntityList(resultInfoDto.getList()));
        return resultParam;
    }

    private List<CommentInfoEntity> buildCommentInfoEntityList(List<CommentDetailInfoDto> list) {
        if(CollectionUtils.isEmpty(list)){
            return new ArrayList<>();
        }
        List<CommentInfoEntity> resultList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            CommentDetailInfoDto source = list.get(i);
            if(source==null){
                continue;
            }
            CommentInfoEntity target = new CommentInfoEntity();
            target.setUserId(source.getUserId()+"");
            target.setCommentId(source.getId()+"");
            target.setModule(source.getModule());
            target.setResourceId(source.getResourceId()+"");
            target.setContent(source.getContent());
            target.setContentTime(DateUtils.date2Str(source.getCreateTime(),DateUtils.YYYY_MM_DD_HH_MM_SS));
            target.setStarNum(source.getStarNum());
            target.setAvatar(null);
            target.setUsername(null);
            target.setReplyNum(null);
            target.setStatus(source.getStatus());
            target.setReplyList(null);
            resultList.add(target);

        }
        return resultList;
    }

    private CommentInfoDto buildCommentInfoDto(QueryCommentRequestParam param) {
        if(param==null){
            return null;
        }
        CommentInfoDto commentInfoDto = new CommentInfoDto();
        commentInfoDto.setUserId(param.getUserId()!=null?Long.valueOf(param.getUserId()):null);
        commentInfoDto.setModule(param.getModule());
        commentInfoDto.setResourceId(param.getResourceId()!=null?Long.valueOf(param.getResourceId()):null);
        commentInfoDto.setScore(param.getScore());
        commentInfoDto.setOrder(param.getOrder());
        commentInfoDto.setPageNum(param.getPageNum());
        commentInfoDto.setPageSize(param.getPageSize());

        return commentInfoDto;
    }
}
