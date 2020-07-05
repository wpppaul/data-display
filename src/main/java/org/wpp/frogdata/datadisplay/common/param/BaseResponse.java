package org.wpp.frogdata.datadisplay.common.param;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.wpp.frogdata.datadisplay.common.code.ResultCode;

/**
 * @Description:  
 * @Param: 
 * @Return: 
 * @Author: wupp
 * @Date: 2020/7/4 17:01
 */
@Data
@Slf4j
public class BaseResponse<T> {

    private int code;
    private String message;
    private T data;
    private boolean success;

    public BaseResponse() {
    }

    public BaseResponse message(String message) {
        setMessage(message);
        return this;
    }

    public static <T> BaseResponse<T> ok(ResultCode resultCode, T data) {
        BaseResponse res = new BaseResponse<>();
        res.setMessage(resultCode.message());
        res.setCode(resultCode.code());
        res.setData(data);
        res.setSuccess(true);
        return res;
    }

    public static BaseResponse ok(ResultCode resultCode) {
        BaseResponse res = new BaseResponse<>();
        res.setMessage(resultCode.message());
        res.setCode(resultCode.code());
        res.setSuccess(true);
        return res;
    }

    public static BaseResponse fail(ResultCode resultCode) {
        BaseResponse res = new BaseResponse();
        res.setMessage(resultCode.message());
        res.setCode(resultCode.code());
        res.setSuccess(false);
        return res;
    }

    public static <T> BaseResponse<T> fail(ResultCode resultCode, T data) {
        BaseResponse res = new BaseResponse();
        res.setMessage(resultCode.message());
        res.setCode(resultCode.code());
        res.setData(data);
        res.setSuccess(false);
        return res;
    }

}
