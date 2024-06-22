package com.datoucai.controller;

import com.datoucai.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 评论Controller
 */
@RestController
@RequestMapping("/comment")
public class CommentController {


    @Autowired
    private IUserService userService;
    @RequestMapping(value = "/query",method = RequestMethod.GET)
    public String query(){
        int userTotal = userService.countUserTotal();
        // 查询一下用户总人数
        return "大头菜-用户总人数:"+userTotal;
    }
}
