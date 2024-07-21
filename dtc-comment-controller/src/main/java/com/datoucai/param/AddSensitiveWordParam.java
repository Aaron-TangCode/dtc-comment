package com.datoucai.param;

import lombok.Data;

/**
 * 增加敏感词-入参
 */
@Data
public class AddSensitiveWordParam {

    /**
     * 敏感词
     */
    private String word;

    /**
     * 类别
     */
    private String category;
}
