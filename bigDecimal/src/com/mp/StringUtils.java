package com.mp;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串工具类
 *
 * @author lvlu
 * @date 2018-01-15 10:13
 **/
public class StringUtils  {

    static Pattern NUMBERIC_PATTERN = Pattern.compile("^[0-9]+(.[0-9]+)?$");

    /**
     * 判断字符串是否为数字
     *
     * @param str
     * @return
     */
    public static boolean isNumeric(String str) {

        Matcher isNum = NUMBERIC_PATTERN.matcher(str);
        if (!isNum.matches()) {
            return false;
        }
        return true;
    }


    public static boolean isChinese(char c){
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
        if(ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
                ||ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
                ||ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
                ||ub == Character.UnicodeBlock.GENERAL_PUNCTUATION
                ||ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
                ||ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS
                ){
            return true;
        }

        return false;
    }
    public static boolean isChinese(String str){
        char[] ch =  str.toCharArray();
        for (char c : ch) {
            if(isChinese(c)){
                return true;
            }
        }
        return false;
    }

    /**
     * 判断字符串中是否含有中文
     */
    public static boolean isCNChar(String s) {
        if (DataUtil.isEmpty(s)) return false;
        boolean booleanValue = false;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c > 128) {
                booleanValue = true;
                break;
            }
        }
        return booleanValue;
    }

    static SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");

    /**
     * 获取订单号
     */
    public static String getOrderNo() {
        StringBuffer sb = new StringBuffer();
        sb.append(sdf.format(new Date()));
        sb.append(stringToAscii("zanclick"));
        sb.append(createRandom(true, 4));
        return sb.toString();
    }

    /**
     * 获取商户号
     */
    public static String getMerchantNo() {
        StringBuffer sb = new StringBuffer();
        sb.append(sdf.format(new Date()));
        sb.append(stringToAscii("xs"));
        sb.append(createRandom(true, 4));
        return sb.toString();
    }

    /**
     * 获取平台ID
     */
    public static String getAppId() {
        StringBuffer sb = new StringBuffer();
        sb.append(sdf.format(new Date()));
        sb.append(stringToAscii("x"));
        sb.append(createRandom(true, 4));
        return sb.toString();
    }

    /**
     * 获取平台ID
     */
    public static String getQrCodeNo() {
        StringBuffer sb = new StringBuffer();
        sb.append("QR");
        sb.append(sdf.format(new Date()));
        sb.append(createRandom(true, 4));
        return sb.toString();
    }

    /**
     * 字符串转 ASCII码
     *
     * @param value
     */
    public static String stringToAscii(String value) {
        StringBuffer sbu = new StringBuffer();
        char[] chars = value.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            sbu.append((int) chars[i]);
        }
        return sbu.toString();
    }


    /**
     * 交易单号创建
     */
    public static String getTradeNo() {
        return System.currentTimeMillis() + createRandom(true, 8);
    }


    /**
     * 创建指定位数的随机数
     *
     * @param numberFlag 是否为纯数字
     * @param length     随机字符串长度
     */
    public static String createRandom(boolean numberFlag, int length) {
        String retStr = "";
        String strTable = numberFlag ? "1234567890" : "1234567890abcdefghijkmnpqrstuvwxyz";
        int len = strTable.length();
        boolean bDone = true;

        do {
            retStr = "";
            int count = 0;

            for (int i = 0; i < length; ++i) {
                double dblR = Math.random() * (double) len;
                int intR = (int) Math.floor(dblR);
                char c = strTable.charAt(intR);
                if ('0' <= c && c <= '9') {
                    ++count;
                }

                retStr = retStr + strTable.charAt(intR);
            }

            if (count >= 2) {
                bDone = false;
            }
        } while (bDone);

        return retStr;
    }

    /**
     * 创建指定位数的随机字符串
     */
    public static String createRandomStr() {
        String retStr = "";
        String strTable = "1234567890";
        String str = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        Random random = new Random();
        for (int i = 0; i <= 5; i++) {
            if (i <= 2) {
                retStr += str.charAt(random.nextInt(str.length()));
            } else {
                retStr += strTable.charAt(random.nextInt(strTable.length()));
            }
        }
        return retStr;
    }

    /**
     * 去掉字符串里的空格
     *
     * @param str
     */
    public static String trim(String str) {
        return str == null ? null : str.trim();
    }

    /**
     * 判断是否为电话号码
     *
     * @param phone
     */
    public static boolean isPhone(String phone) {
        String regex = "^((13[0-9])|(14[5,7,9])|(15([0-3]|[5-9]))|(166)|(17[0,1,3,5,6,7,8])|(18[0-9])|(19[8|9]))\\d{8}$";
        if (phone.length() != 11) {
            return false;
        } else {
            Pattern p = Pattern.compile(regex);
            Matcher m = p.matcher(phone);
            boolean isMatch = m.matches();
            return isMatch;
        }
    }

    /**
     * 判断是否为邮箱
     *
     * @param string
     */
    public static boolean isEmail(String string) {
        String regEx1 = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
        if (string == null) {
            return false;
        }
        Pattern p = Pattern.compile(regEx1);
        Matcher m = p.matcher(string);
        if (m.matches()) {
            return true;
        } else {
            return false;
        }
    }

    public static String getMethodName(String method) {
        String[] arr = method.split("\\.");
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < arr.length; i++) {
            String s = arr[i];
            if (i > 0) {
                sb.append(upperCaseFirst(s));
            } else {
                sb.append(s);
            }
        }
        return sb.toString();
    }

    static String upperCaseFirst(String str) {
        char[] ch = str.toCharArray();
        if (ch[0] >= 'a' && ch[0] <= 'z') {
            ch[0] = (char) (ch[0] - 32);
        }
        return new String(ch);
    }
}
