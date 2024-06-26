package com.datoucai.controller;

import com.alibaba.fastjson.JSON;
import com.datoucai.param.*;
import com.datoucai.service.ICommentService;
import com.datoucai.service.dto.CommentDetailDto;
import com.datoucai.service.dto.CommentInfoDto;
import com.datoucai.service.dto.CommentInfoResultDto;
import com.datoucai.service.dto.QueryCommentInfoDto;
import com.datoucai.utils.BaseResultUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
            log.info("增加评论-controller层-入参:{}", JSON.toJSONString(param));
            CommentInfoDto target = new CommentInfoDto();
            target.setId(null);
            target.setUserId(Long.valueOf(param.getUserId()));
            target.setModule(param.getModule());
            target.setStatus(param.getModule());
            target.setResourceId(Long.valueOf(param.getResourceId()));
            target.setContent(param.getContent());
            target.setStarNum(0);
            target.setScore(param.getScore());
            target.setIsDelete(0);
            target.setCreateTime(new Date());
            target.setUpdateTime(new Date());
            int count = commentService.addComment(target);
            log.info("增加评论-controller层-出参:{}", count);
            return BaseResultUtils.generateSuccess(count>0);
        }catch (Exception e){
            log.error("增加评论-controller层-异常:", e);
            return BaseResultUtils.generateFail(e.getMessage());
        }
    }
    /**
     * 删除评论
     * @param param
     * @return
     */
    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    public BaseResult<Boolean> deleteComment(@RequestBody DelCommentRequestParam param){
        try {
            log.info("删除评论-controller层-入参:{}", JSON.toJSONString(param));
            CommentInfoDto dto = new CommentInfoDto();
            dto.setId(Long.valueOf(param.getCommentId()));
            dto.setUserId(Long.valueOf(param.getUserId()));
            dto.setModule(param.getModule());
            dto.setStatus(null);
            dto.setResourceId(Long.valueOf(param.getResourceId()));
            dto.setUpdateTime(new Date());
            int count = commentService.deleteComment(dto);
            log.info("删除评论-controller层-出参:{}", count);
            return BaseResultUtils.generateSuccess(count>0);
        }catch (Exception e){
            log.error("删除评论-controller层-异常:", e);
            return BaseResultUtils.generateFail(e.getMessage());
        }
    }
    /**
     * 查询评论
     * @param param
     * @return
     */
    @RequestMapping(value = "/query",method = RequestMethod.GET)
    public BaseResult<CommentResultParam> queryComment(QueryCommentRequestParam param){
        try {
            log.info("查询评论-controller层-入参:{}", JSON.toJSONString(param));
            QueryCommentInfoDto dto = new QueryCommentInfoDto();
            BeanUtils.copyProperties(param,dto);
            CommentInfoResultDto commentDetailDtos = commentService.queryCommentByParam(dto);
            CommentResultParam result = buildCommentResultParam(commentDetailDtos);
            log.info("查询评论-controller层-出参:{}", JSON.toJSONString(param));
            return BaseResultUtils.generateSuccess(result);
        }catch (Exception e){
            log.error("查询评论-controller层-异常:", e);
            return BaseResultUtils.generateFail(e.getMessage());
        }
    }

    private CommentResultParam buildCommentResultParam(CommentInfoResultDto commentDetailDtos) {
        if(commentDetailDtos==null){
            return null;
        }
        CommentResultParam commentResultParam = new CommentResultParam();
        commentResultParam.setTotal(commentDetailDtos.getTotal());
        commentResultParam.setList(buildList(commentDetailDtos.getList()));
        return commentResultParam;
    }

    private List<CommentInfoEntity> buildList(List<CommentDetailDto> list) {
        if(CollectionUtils.isEmpty(list)){
            return new ArrayList<>();
        }
        List<CommentInfoEntity> resultList = new ArrayList<>();

        for (int i = 0; i < list.size(); i++) {
            CommentDetailDto source = list.get(i);
            CommentInfoEntity target = new CommentInfoEntity();
            target.setUserId(source.getUserId()+"");
            target.setCommentId(source.getId()+"");
            target.setModule(source.getModule());
            target.setResourceId(source.getResourceId()+"");
            target.setContent(source.getContent());
            target.setContentTime(null);
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
}
