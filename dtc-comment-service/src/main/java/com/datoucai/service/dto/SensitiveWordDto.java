package com.datoucai.service.dto;

import lombok.Data;


@Data
public class SensitiveWordDto {

    /**
     * 敏感词
     */
    private String word;

    /**
     * 类别
     */
    private String category;

}
