package com.mp.common.api;

import lombok.Data;

/**
 * 公共请求参数
 *
 * @author Administrator
 * @date 2019-9-24 15:14:41
 */
@Data
public class AsiaInfoHeader {
    private String appId;
    private String timestamp;
    private String busiSerial;
    private String sign;
    private String sign_method;
    private String nonce;
    private String authCode;
    private String operatorid;
    private String comflowcode;
    private String instanceid;
    private String route_type;
    private String route_value;
    private String unitid;

    public static AsiaInfoHeader dev() {
        AsiaInfoHeader header = new AsiaInfoHeader();
        header.setAppId("502004");

        return header;
    }
}
