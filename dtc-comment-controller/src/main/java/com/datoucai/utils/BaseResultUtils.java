package com.datoucai.utils;

import com.datoucai.param.BaseResult;

/**
 * 响应工具类
 */
public class BaseResultUtils {
    /**
     * 成功
     * @param t
     * @return
     * @param <T>
     */
    public static <T> BaseResult<T> generateSuccess(T t){
        BaseResult<T> baseResult = new BaseResult<>();
        baseResult.setCode(0);
        baseResult.setSuccess(true);
        baseResult.setMsg("操作成功");
        baseResult.setData(t);
        return baseResult;
    }

    /**
     * 失败
     * @return
     */
    public static  BaseResult generateFail(Integer code,String msg){
        BaseResult baseResult = new BaseResult<>();
        baseResult.setCode(code);
        baseResult.setSuccess(false);
        baseResult.setMsg(msg);
        baseResult.setData(null);
        return baseResult;
    }
    /**
     * 失败
     * @return
     */
    public static  BaseResult generateFail(String msg){
        BaseResult baseResult = new BaseResult<>();
        baseResult.setCode(-1);
        baseResult.setSuccess(false);
        baseResult.setMsg(msg);
        baseResult.setData(null);
        return baseResult;
    }
}
