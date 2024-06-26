package com.datoucai.service;

import com.datoucai.dto.CommentInfoDto;
import com.datoucai.dto.CommentResultInfoDto;

public interface ICommentService {

    int addComment(CommentInfoDto dto);

    int deleteComment(CommentInfoDto dto);

    CommentResultInfoDto queryCommentByParam(CommentInfoDto dto);
}
