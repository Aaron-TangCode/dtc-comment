package com.datoucai.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 评论Controller
 */
@RestController
@RequestMapping("/comment")
public class CommentController {

    @RequestMapping(value = "/query",method = RequestMethod.GET)
    public String query(){
        return "alreadly springmvc for comment project";
    }
}
