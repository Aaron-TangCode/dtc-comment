package com.datoucai.controller;

import com.datoucai.dto.CommentInfoDto;
import com.datoucai.param.AddCommentRequestParam;
import com.datoucai.param.BaseResult;
import com.datoucai.param.CommentResultParam;
import com.datoucai.param.DelCommentRequestParam;
import com.datoucai.param.QueryCommentRequestParam;
import com.datoucai.service.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/comment")
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
        int count = commentService.addComment(dto);
        return new BaseResult(0,true,"添加成功",count>0);
    }
    /**
     * 删除评论
     * @param param
     * @return
     */
    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    public BaseResult<Boolean> deleteComment(@RequestBody DelCommentRequestParam param){
        CommentInfoDto dto = new CommentInfoDto();
        dto.setId(Long.valueOf(param.getCommentId()));
        dto.setUserId(Long.valueOf(param.getUserId()));
        dto.setModule(param.getModule());
        dto.setResourceId(Long.valueOf(param.getResourceId()));
        dto.setUpdateTime(new Date());
        int count = commentService.deleteComment(dto);
        return new BaseResult(0,true,"删除成功",count>0);
    }
    /**
     * 查询评论
     * @param param
     * @return
     */
    @RequestMapping(value = "/query",method = RequestMethod.GET)
    public BaseResult<CommentResultParam> queryComment(QueryCommentRequestParam param){
        return null;
    }
}
