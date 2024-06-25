package com.datoucai.utils;

import com.datoucai.param.BaseResult;

/**
 * 结果-工具类
 */
public class BaseResultUtils {
    public static <T> BaseResult<T> generateSuccess(T t){
        BaseResult baseResult = new BaseResult();
        baseResult.setCode(0);
        baseResult.setSuccess(true);
        baseResult.setMsg("操作成功");
        baseResult.setData(t);

        return baseResult;

    }
    public static <T> BaseResult<T> generateFail(Integer code,String msg){
        BaseResult baseResult = new BaseResult();
        baseResult.setCode(code);
        baseResult.setSuccess(false);
        baseResult.setMsg(msg);
        baseResult.setData(false);
        return baseResult;
    }
    public static <T> BaseResult<T> generateFail(String msg){
        BaseResult baseResult = new BaseResult();
        baseResult.setCode(-1);
        baseResult.setSuccess(false);
        baseResult.setMsg(msg);
        baseResult.setData(false);
        return baseResult;
    }
}
