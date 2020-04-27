package com.mp.common.resolver;


/**
 * 请求通用接口
 *
 * @author duchong
 * @date 2019-9-5 16:41:15
 **/
public interface ApiRequestResolver {

    /**
     * 通用接口
     *
     * @param appId 应用ID
     * @param cipherJson 加密参数
     * @param request 请求内容
     * @return
     */
     String resolve(String appId, String cipherJson, String request);

}
