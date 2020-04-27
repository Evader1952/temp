package com.mp.common.entity;

import com.alibaba.fastjson.JSONObject;
import com.mp.common.utils.DataUtil;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author duchong
 * @description
 * @date
 */
public abstract class Param implements Serializable {
    /**
     * 检查不为空
     *
     * @param param 参数
     * @return
     */
    protected boolean checkNotNull(Object param) {
        return DataUtil.isNotEmpty(param);
    }

    /**
     * 检查为空
     *
     * @param param 参数
     * @return
     */
    protected boolean checkNull(Object param) {
        return DataUtil.isEmpty(param);
    }

    /**
     * 检查长度
     *
     * @param param  参数
     * @param length 最大长度
     * @return
     */
    protected boolean checkLength(String param, Integer length) {
        if (param.length() > length) {
            return true;
        }
        return false;
    }


    /**
     * 检查金额格式
     *
     * @param param 参数
     * @return
     */
    protected boolean checkMoneyFormat(String param) {
        try {
            new BigDecimal(param);
        } catch (Exception e) {
            return true;
        }
        return false;
    }

    /**
     * 检查金额格式
     *
     * @param param 参数
     * @return
     */
    protected String formatMoney(String param) {
        return new BigDecimal(param).setScale(2, BigDecimal.ROUND_HALF_EVEN).toString();
    }

    /**
     * 检查金额信息
     *
     * @param param 参数
     * @param max   最大金额
     * @param min   最小金额
     * @return
     */
    protected boolean checkMoney(String param, String max, String min) {
        BigDecimal maxMoney = new BigDecimal(max);
        BigDecimal minMoney = new BigDecimal(min);
        BigDecimal money = new BigDecimal(param);
        Integer maxJudge = money.compareTo(maxMoney);
        Integer minJudge = money.compareTo(minMoney);
        if (maxJudge > 0) {
            return true;
        }
        if (minJudge < 0) {
            return true;
        }
        return false;
    }


    /**
     * 转换数组
     *
     * @param param  参数
     * @param tClass 类型
     * @return
     */
    protected <T> List<T> parseArray(String param, Class<T> tClass) {
        try {
            return JSONObject.parseArray(param, tClass);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 检查长度
     *
     * @param param  参数
     * @param tClass 类型
     * @return
     */
    protected <T> T parseObject(String param, Class<T> tClass) {
        try {
            return JSONObject.parseObject(param, tClass);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 检查该参数是否符合要求
     *
     * @param param 参数
     * @return
     */
    protected boolean checkEquals(Object param, Object... objects) {
        if (checkNull(param) || checkNull(param) || objects.length == 0) {
            return true;
        }
        Integer num = 0;
        for (Object o : objects) {
            if (param.equals(o)) {
                num++;
            }
        }
        return num.equals(0);
    }


    /**
     * 转换成JSON字符串
     *
     * @return
     */
    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
