package com.datoucai.param;

import lombok.Data;

@Data
public class AddSensitiveWordParam {
    /**
     * 敏感词
     */
    private String word;
    /**
     * 敏感词类型
     */
    private String category;
}
