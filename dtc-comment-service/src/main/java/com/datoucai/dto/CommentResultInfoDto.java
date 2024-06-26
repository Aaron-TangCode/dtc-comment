package com.datoucai.dto;

import lombok.Data;

import java.util.List;

/**
 * 评论结果集
 */
@Data
public class CommentResultInfoDto {

    private Long total;

    private List<CommentDetailInfoDto> list;
}
