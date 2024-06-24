package com.datoucai.controller;

import com.datoucai.param.AddCommentRequestParam;
import com.datoucai.param.BaseResult;
import com.datoucai.param.CommentResultParam;
import com.datoucai.param.DelCommentRequestParam;
import com.datoucai.param.QueryCommentRequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/comment")
public class CommentController {

    /**
     * 增加评论
     * @param param
     * @return
     */
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public BaseResult<Boolean> addComment(AddCommentRequestParam param){
        return null;
    }
    /**
     * 删除评论
     * @param param
     * @return
     */
    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    public BaseResult<Boolean> deleteComment(DelCommentRequestParam param){
        return null;
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
