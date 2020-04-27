package com.mp.common.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author duchong
 * @description
 * @date
 */
@Data
public class RequestParam extends Param implements Serializable {
    /**
     * 方法名
     */
    private String method;

    /**
     * 请求内容
     */
    private String content;

    /**
     * 参数检查
     *
     * @return 返回检查结果
     */
    public String check(){
        if (checkNull(method)){
            return "缺少方法名称";
        }
        if (checkNull(content)){
            return "缺少请求内容";
        }
        return null;
    }
}
