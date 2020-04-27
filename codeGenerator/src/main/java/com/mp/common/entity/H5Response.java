package com.mp.common.entity;

import lombok.Data;

/**
 * h5页面响应码
 *
 * @author duchong
 * @date 2019-9-10 11:12:32
 */
@Data
public class H5Response<T> {

    private String code;

    private String msg;

    private T data;
}
