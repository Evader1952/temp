package com.mp.common.api;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * 工具类
 *
 * @author Administrator
 * @date 2019-9-24 15:14:41
 */
public class AsiaInfoUtil {

    private static SimpleDateFormat yyyyMMddHHmmssSSS = new SimpleDateFormat("yyyyMMddHHmmssSSS");


    public static AsiaInfoHeader header(String route_value) {
        AsiaInfoHeader header = new AsiaInfoHeader();
        header.setAppId(RestConfig.appId);
        header.setTimestamp(yyyyMMddHHmmssSSS.format(new Date()));
        header.setBusiSerial(busiSerial());
        header.setNonce(nonce());
        header.setSign_method(RestConfig.signMethod);
        header.setRoute_type(RestConfig.routeType);
        header.setRoute_value(route_value);
        header.setOperatorid("");
        return header;
    }

    static String busiSerial(){
        return UUID.randomUUID().toString().replaceAll("-","");
    }

    /**
     * 创建指定数量的随机字符串
     * @return
     */
    static String nonce() {
        String retStr = "";
        String strTable = "1234567890ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        int len = strTable.length();
        boolean bDone = true;
        do {
            retStr = "";
            int count = 0;
            for (int i = 0; i < 32; i++) {
                double dblR = Math.random() * len;
                int intR = (int) Math.floor(dblR);
                char c = strTable.charAt(intR);
                if (('0' <= c) && (c <= '9')) {
                    count++;
                }
                retStr += strTable.charAt(intR);
            }
            if (count >= 2) {
                bDone = false;
            }
        } while (bDone);
        return retStr;
    }
}
