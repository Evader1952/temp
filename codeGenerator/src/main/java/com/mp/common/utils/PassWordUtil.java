package com.mp.common.utils;

import com.google.common.base.Preconditions;
import org.apache.commons.codec.binary.Hex;

import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Random;

/**
 * 密码加密算法
 *
 * @author lvlu
 * @date 2018-10-17 15:49
 **/
public class PassWordUtil {

    public static final int ITERATIONS = 1024;

    public static String generateSalt(){
        return generateSalt(8);
    }

    public static String generateSalt(int numBytes){
        Preconditions.checkArgument(numBytes > 0, "numBytes argument must be a positive integer (1 or larger)", (long)numBytes);
        Random ranGen = new SecureRandom();
        byte[] aesKey = new byte[numBytes];
        ranGen.nextBytes(aesKey);
        return encodeHex(aesKey);
    }

    public static String generatePasswordSha1WithSalt(String pwd,String salt) {
        return generatePasswordSha1WithSalt(pwd,salt,ITERATIONS);
    }

    public static String generatePasswordSha1WithSalt(String pwd,String salt,int iterations) {
        byte[] saltByte = decodeHex(salt);
        byte[] password = digest("SHA-1",pwd.getBytes(),saltByte,iterations);
        return encodeHex(password);
    }

    public static String generatePasswordSha1(String pwd, int iterations) {
        byte[] password = digest("SHA-1",pwd.getBytes(), null,iterations);
        return encodeHex(password);
    }


    public static byte[] digest(String algorithm,byte[] input,byte[] salt,int iterations){
        try {
            MessageDigest digest = MessageDigest.getInstance(algorithm);
            if (salt != null) {
                digest.update(salt);
            }

            byte[] result = digest.digest(input);
            for(int i = 1; i < iterations; ++i) {
                digest.reset();
                result = digest.digest(result);
            }

            return result;
        } catch (GeneralSecurityException e) {
            throw  new RuntimeException(e);
        }
    }

    public static String encodeHex(byte[] input) {
        return Hex.encodeHexString(input);
    }

    public static byte[] decodeHex(String input) {
        try {
            return Hex.decodeHex(input.toCharArray());
        } catch (Exception e) {
            throw  new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        System.out.println(generatePasswordSha1("123456",8));
    }
}
