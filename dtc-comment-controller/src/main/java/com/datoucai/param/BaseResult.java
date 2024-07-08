package com.datoucai.param;

import lombok.Data;

/**
 * 通用返回结果
 * @param <T>
 */
@Data
public class BaseResult<T> {
    /**
     * 响应码
     */
    private Integer code;
    /**
     * 是否成功
     * true: 成功
     * false: 失败
     */
    private Boolean success;
    /**
     * message
     */
    private String msg;
    /**
     * 具体的返回值
     */
    private T data;

    public BaseResult(Integer code, Boolean success, String msg, T data) {
        this.code = code;
        this.success = success;
        this.msg = msg;
        this.data = data;
    }

    public BaseResult() {
    }
}
