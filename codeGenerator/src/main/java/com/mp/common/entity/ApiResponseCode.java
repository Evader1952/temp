package com.mp.common.entity;


import com.mp.common.utils.DataUtil;

/**
 * h5页面响应码
 *
 * @author duchong
 * @date 2019-9-10 11:12:32
 */
public class ApiResponseCode {

    /**
     * 成功响应码
     */
    public static String SUCCESS = "20000";

    /**
     * 失败响应码
     */
    public static String FAIL = "10500";

    /**
     * 是否为成功响应码
     *
     * @param code
     * @return
     */
    public static Boolean isSuccess(String code) {
        if (DataUtil.isEmpty(code)) {
            return false;
        }
        return SUCCESS.equals(code);
    }

    /**
     * 是否为失败应码
     *
     * @param code
     * @return
     */
    public static Boolean isFail(String code) {
        if (DataUtil.isEmpty(code)) {
            return false;
        }
        return FAIL.equals(code);
    }
}
