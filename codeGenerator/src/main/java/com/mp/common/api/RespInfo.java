package com.mp.common.api;

import lombok.Data;

/**
 * @author duchong
 * @description 通用返回内容
 * @date
 */
@Data
public class RespInfo {

    private String respcode;

    private String respdesc;

    private String resptype;

    private Result result;

    public boolean isSuccess(){
        return RespCode.SUCCESS.getCode().equals(respcode);
    }

    public enum RespCode{
        SUCCESS("0");
        private String code;

        RespCode(String code) {
            this.code = code;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }
    }
}
