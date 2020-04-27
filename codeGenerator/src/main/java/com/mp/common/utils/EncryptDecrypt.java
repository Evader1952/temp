package com.mp.common.utils;

import java.math.BigInteger;

/**
 * Created by lvlu on 2016/4/20.
 */
public class EncryptDecrypt {
    private static final int RADIX = 16;
    private static final String SEED = "0933910847463829232312312";
//

    public static String encrypt(String password) {
        if (password == null) {
            return "";
        }
        if (password.length() == 0) {
            return "";
        }
        BigInteger bi_passwd = new BigInteger(password.getBytes());
        BigInteger bi_r0 = new BigInteger(SEED);
        BigInteger bi_r1 = bi_r0.xor(bi_passwd);
        return bi_r1.toString(RADIX);
    }

    public static String decrypt(String encrypted) {
        if (encrypted == null) {
            return "";
        }
        if (encrypted.length() == 0) {
            return "";
        }

        BigInteger bi_confuse = new BigInteger(SEED);
        try {
            BigInteger bi_r1 = new BigInteger(encrypted, RADIX);
            BigInteger bi_r0 = bi_r1.xor(bi_confuse);
            return new String(bi_r0.toByteArray());
        } catch (Exception e) {
           e.printStackTrace();
            return "";
        }
    }

    public static void main(String[] args) {
        System.out.println(encrypt("QR201908121401467650"));
    }
}
