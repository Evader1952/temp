package com.mp.common.entity;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;

import java.io.Serializable;

/**
 * 请求结果返回
 *
 * @author duchong
 * @date 2019-7-5 10:25:29
 */
@Data
public class ResponseParam<T> extends Param implements Serializable {

    private String code;

    private String message;

    private T data;

    public void setSuccess(){
        this.code = ApiResponseCode.SUCCESS;
    }

    public boolean isSuccess(){
        return ApiResponseCode.SUCCESS.equals(code);
    }

    public void setFail(){
        this.code = ApiResponseCode.FAIL;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
