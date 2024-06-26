package com.datoucai.service;

import com.datoucai.dto.CommentInfoDto;

public interface ICommentService {

    int addComment(CommentInfoDto dto);

    int deleteComment(CommentInfoDto dto);
}
