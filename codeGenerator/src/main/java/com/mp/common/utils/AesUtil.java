package com.mp.common.utils;


import lombok.extern.slf4j.Slf4j;

/**
 * @author tuchuan
 * @description
 * @date 2017/6/19 15:45
 */
@Slf4j
public class AesUtil {

//    private static String key = "12345678qwertyui";
//
//    /**
//     * 加密
//     *
//     * @param value
//     * @return
//     **/
//    public static String Encrypt(String value) {
//        return Encrypt(value,key);
//    }
//
//    /**
//     * 解密
//     *
//     * @param value
//     * @return
//     */
//    public static String Decrypt(String value) {
//        return Decrypt(value,key);
//    }

    /**
     * 加密
     *
     * @param value
     * @return
     **/
    public static String Encrypt(String value,String key) {
        try {
            return Aes.Encrypt(value, key);
        } catch (Exception e) {
            log.error("加密错误:{}",e);
        }
        return null;
    }

    /**
     * 解密
     *
     * @param value
     * @return
     */
    public static String Decrypt(String value,String key) {
        try {
            return Aes.Decrypt(value, key);
        } catch (Exception e) {
            log.error("解密错误:{}",e);
        }
        return null;
    }
}
