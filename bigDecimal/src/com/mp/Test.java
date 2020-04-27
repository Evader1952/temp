package com.mp;

import java.math.BigDecimal;

public class Test {
    public static void main(String[] args) {
        BigDecimal bigDecimal = new BigDecimal("0.33300000");

        int i = bigDecimal.compareTo(new BigDecimal("0.333"));
//        System.out.println(i);
//
//        System.out.println(MoneyUtil.judgeMoney( "111","11"));
//
//        MoneyUtil.largeMoney("","");
//
//        System.out.println(MoneyUtil.subtract("0.222", "0.111111111111111"));
//
//        System.out.println(MoneyUtil.add("0.2222", "0.29"));


        //  System.out.println(MoneyUtil.moneyRounding("111.444", 3));
        //四舍五入  ROUND_HALF_EVEN
        // System.out.println(MoneyUtil.formatMoney("1199.6944"));
        //System.out.println(MoneyUtil.formatRate("1199"));

        //除法
        System.out.println(MoneyUtil.divide("10", "3"));

        System.out.println(MoneyUtil.zeroMoney("0.001"));
    }
}
