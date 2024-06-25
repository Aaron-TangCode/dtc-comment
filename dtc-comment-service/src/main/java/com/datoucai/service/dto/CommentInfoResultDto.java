package com.datoucai.service.dto;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * @Author <a href="mailto:yebing.thl@alibaba-inc.com">叶冰</a>
 * @Date：2024/6/25 18:13
 */
@Data
public class CommentInfoResultDto {

    private Long total;

    private List<CommentDetailDto> list;
}
