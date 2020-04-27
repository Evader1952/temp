package com.mp.common.api;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @author duchong
 * @description 广州直连能力平台配置信息
 * @date 2019-11-5 15:15:31
 */
@Configuration
public class RestConfig {

    public static String payOrderNotify;

    public static String requestPrivateKey;

    public static String appId;

    public static String routeType;

    public static String signMethod;

    @Value("${guangzhou.request.notify}")
    public void setPayOrderNotify(String payOrderNotify) {
        RestConfig.payOrderNotify = payOrderNotify;
    }

    @Value("${guangzhou.request.key}")
    public void setRequestPrivateKey(String requestPrivateKey) {
        RestConfig.requestPrivateKey = requestPrivateKey;
    }

    @Value("${guangzhou.request.appId}")
    public void setAppId(String appId) {
        RestConfig.appId = appId;
    }

    @Value("${guangzhou.request.routeType}")
    public void setRouteType(String routeType) {
        RestConfig.routeType = routeType;
    }

    @Value("${guangzhou.request.signMethod}")
    public void setSignMethod(String signMethod) {
        RestConfig.signMethod = signMethod;
    }

    public static Boolean isPhone(){
        return RestConfig.routeType.equals("1");
    }
}
