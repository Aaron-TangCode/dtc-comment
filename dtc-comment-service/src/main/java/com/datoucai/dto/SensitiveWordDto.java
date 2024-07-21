package com.datoucai.dto;

import lombok.Data;

/**
 * 敏感词DTO
 */
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
