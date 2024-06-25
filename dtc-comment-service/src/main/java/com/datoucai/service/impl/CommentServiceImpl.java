package com.datoucai.service.impl;

import com.datoucai.dto.CommentInfoDto;
import com.datoucai.entity.CommentEntity;
import com.datoucai.mapper.CommentMapper;
import com.datoucai.service.ICommentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 评论service
 */
@Service
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
}
