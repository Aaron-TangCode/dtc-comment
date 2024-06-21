package com.datoucai.controller;

import com.datoucai.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 评论Controller
 */
@RestController
@RequestMapping("/comment")
//@Slf4j
public class CommentController {


    Logger log = LoggerFactory.getLogger(CommentController.class);
    @Autowired
    private IUserService userService;

    @RequestMapping(value = "/query",method = RequestMethod.GET)
    public String query(){
        int num = userService.countUser();
        return "用户人数="+num;
    }
    @RequestMapping(value = "/test/log",method = RequestMethod.GET)
    public String testLog(){
        log.trace("trace日志");
        log.debug("debug日志");
        log.info("info日志");
        log.warn("warn日志");
        log.error("error日志");
        return "日志测试";
    }
}
