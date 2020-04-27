package com.mp.common.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author duchong
 * @description
 * @date
 */
public class Method {


    static List<String> methods = new ArrayList<>();

    static {
        methods.add("comZanclickVerifyIdentity");
        methods.add("comZanclickCreateMerchant");
        methods.add("comZanclickVerifyMerchant");
        methods.add("comZanclickQueryAuthOrderList");
        methods.add("comZanclickQueryAuthOrder");
        methods.add("comZanclickRefundAuthPay");
        methods.add("comZanclickQueryAuthPay");
        methods.add("comZanclickCreateAuthPrePay");
        methods.add("comZanclickCreateOrder");
        methods.add("comZanclickQueryAuthOrderDetail");
    }

    public static boolean hasMethod(String methodName){
        return methods.contains(methodName);
    }
}
