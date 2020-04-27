package com.mp;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * @author duchong
 * @date 2019-4-8 11:31:23
 * @description 金额相关
 */
public class MoneyUtil {

    /**
     * 比较金额1 是否大于 或等于金额2
     *
     * @param money1 金额1
     * @param money2 金额2
     */
    public static boolean judgeMoney(String money1, String money2) {
        if (money1 == null || money2 == null) {
            return false;
        }
        Integer result = new BigDecimal(money1).compareTo(new BigDecimal(money2));
        return result == 0 || result == 1;
    }

    public static boolean largeMoney(String money1, String money2) {
        if (DataUtil.isEmpty(money1)) {
            money1 = "0.00";
        }
        if (DataUtil.isEmpty(money2)) {
            money2 = "0.00";
        }
        try {
            Integer result = new BigDecimal(money1).compareTo(new BigDecimal(money2));
            return result == 1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean equal(String money1, String money2) {
        if (DataUtil.isEmpty(money1)) {
            money1 = "0.00";
        }
        if (DataUtil.isEmpty(money2)) {
            money2 = "0.00";
        }
        try {
            Integer result = new BigDecimal(money1).compareTo(new BigDecimal(money2));
            return result == 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**

    /**
     * 金额相减
     *
     * @param money1 金额1
     * @param money2 金额2
     */
    public static String subtract(String money1, String money2) {
        return new BigDecimal(money1).subtract(new BigDecimal(money2)).toString();
    }

    /**
     * 金额相乘
     *
     * @param money1 金额1
     * @param money2 金额2
     */
    public static String multiply(String money1, String money2) {
        BigDecimal total = new BigDecimal(money1).multiply(new BigDecimal(money2));
        return total.toString();
    }

    /**
     * 金额相加
     *
     * @param money1 金额1
     * @param money2 金额2
     */
    public static String add(String money1, String money2) {
        BigDecimal total = new BigDecimal(money1).add(new BigDecimal(money2)).setScale(2, BigDecimal.ROUND_HALF_EVEN);
        return total.toString();
    }

    /**
     * 金额相加
     *
     * @param money1 金额1
     * @param money2 金额2
     */
    public static String divide(String money1, String money2) {
        BigDecimal total = new BigDecimal(money1).divide(new BigDecimal(money2),2, BigDecimal.ROUND_HALF_EVEN);
        return total.toString();
    }

    public static void main(String[] args) {
        System.err.println(divide("100","12"));
    }

    /**
     * 金额取整
     *
     * @param money
     * @param type  0-不取整 1-分 2-角 3-元
     */
    public static String moneyRounding(String money, Integer type) {
        String value = null;
        if (type == 0) {
            return value;
        }
        Integer index = null;
        if (type == 1) {
            index = 1000;
        } else if (type == 2) {
            index = 100;
        } else {
            index = 10;
        }
        Double s = Double.valueOf(money) * index;
        Double j = s % 100;
        return new BigDecimal(j).divide(new BigDecimal(index)).setScale(2, BigDecimal.ROUND_HALF_EVEN).toString();
    }


    /**
     * 获取金额，若金额为整数，则显示整数，若金额为小数，则保留两位小数
     *
     * @param total_money
     * @return
     */
    public static String formatMoney(String total_money) {
        if (total_money == null) {
            return "0.00";
        }
        BigDecimal decimal = new BigDecimal("0");
        try {
            decimal = decimal.add(new BigDecimal(total_money));
        } catch (Exception e) {
            e.printStackTrace();
            return "0.00";
        }
        return decimal.setScale(2, BigDecimal.ROUND_HALF_EVEN).toString();
    }

    /**
     * 获取金额，若金额为整数，则显示整数，若金额为小数，则保留两位小数
     *
     * @param rate
     * @return
     */
    public static String formatRate(String rate) {
        if (rate == null) {
            return "0.0000";
        }
        BigDecimal decimal = new BigDecimal("0");
        try {
            decimal.add(new BigDecimal(rate));
        } catch (Exception e) {
            e.printStackTrace();
            return "0.0000";
        }
        return decimal.setScale(4, BigDecimal.ROUND_HALF_EVEN).toString();
    }


    /**
     * 获取金额，若金额为整数，则显示整数，若金额为小数，则保留两位小数
     *
     * @param total_money
     * @return
     */
    public static String getMoney(String total_money) {
        if (total_money == null) {
            return "0.00";
        }
        //判断total_money中是否包含 "."
        int ind = total_money.indexOf(".");
        //ind如果不等于-1，代表存在"."
        if (ind != -1) {
            String[] money = total_money.split("\\.");
            Double f = Double.valueOf(money[1]);
            if (f >= 0) {
                BigDecimal b = new BigDecimal(total_money);
                //保留2位小数
                DecimalFormat df = new DecimalFormat("0.00");
                String scale_3 = b.setScale(3, BigDecimal.ROUND_HALF_EVEN).toString();
                String m = df.format(new BigDecimal(scale_3).setScale(2, BigDecimal.ROUND_HALF_EVEN).doubleValue());
                return m;
            } else {
                return money[0];
            }
        } else {
            total_money = total_money + ".00";
        }
        return total_money;
    }

    /**
     * 比较金额 是否大于 0
     *
     * @param number 金额1
     */
    public static boolean zeroMoney(Object number) {
        if (number == null) {
            return false;
        }
        try {
            Integer result = new BigDecimal(String.valueOf(number)).compareTo(new BigDecimal(0));
            return result == 1;
        } catch (Exception e) {
            return false;
        }
    }

}
