package com.mp.common.api;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;

/**
 * @author duchong
 * @description 业务返回内容
 * @date 2019-10-30 15:22:13
 */
@Data
public class Result {

    private String retcode;

    private JSONObject data;

    private String retmsg;

    public boolean isSuccess(){
        return RetCode.SUCCESS.getCode().equals(retcode);
    }

    public enum RetCode{
        SUCCESS("0");
        private String code;

        RetCode(String code) {
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
