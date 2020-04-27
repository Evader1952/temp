package com.mp.common.api;

import lombok.Data;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;


/**
 *
 *
 * @author carver.gu
 * @since 1.0, Sep 13, 2009
 */
@Data
public class AsiaInfoHashMap extends HashMap<String, String> {

    private static final long serialVersionUID = -1277791390393392630L;

    public static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public static final String DATE_TIMEZONE = "GMT+8";

    public AsiaInfoHashMap() {
        super();
    }

    public AsiaInfoHashMap(Map<? extends String, ? extends String> m) {
        super(m);
    }

    public String put(String key, Object value) {
        String strValue;

        if (value == null) {
            strValue = null;
        } else if (value instanceof String) {
            strValue = (String) value;
        } else if (value instanceof Integer) {
            strValue = ((Integer) value).toString();
        } else if (value instanceof Long) {
            strValue = ((Long) value).toString();
        } else if (value instanceof Float) {
            strValue = ((Float) value).toString();
        } else if (value instanceof Double) {
            strValue = ((Double) value).toString();
        } else if (value instanceof Boolean) {
            strValue = ((Boolean) value).toString();
        } else if (value instanceof Date) {
            DateFormat format = new SimpleDateFormat(DATE_TIME_FORMAT);
            format.setTimeZone(TimeZone.getTimeZone(DATE_TIMEZONE));
            strValue = format.format((Date) value);
        } else {
            strValue = value.toString();
        }

        return this.put(key, strValue);
    }


    public static AsiaInfoHashMap toAsiaInfoHashMap(AsiaInfoHeader header) {
        AsiaInfoHashMap map = new AsiaInfoHashMap();
        Field[] fields = header.getClass().getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            if (getFieldValueByName(fields[i].getName(), header) != null) {
                map.put(fields[i].getName(), getFieldValueByName(fields[i].getName(), header));
            }

        }
        return map;
    }

    private static Object getFieldValueByName(String fieldName, Object o) {
        try {
            String firstLetter = fieldName.substring(0, 1).toUpperCase();
            String getter = "get" + firstLetter + fieldName.substring(1);
            Method method = o.getClass().getMethod(getter, new Class[]{});
            Object value = method.invoke(o, new Object[]{});
            return value;
        } catch (Exception e) {
            return null;
        }
    }

    public String put(String key, String value) {
        if (areNotEmpty(key, value)) {
            return super.put(key, value);
        } else {
            return null;
        }
    }

    public static boolean areNotEmpty(String... values) {
        boolean result = true;
        if (values == null || values.length == 0) {
            result = false;
        } else {
            for (String value : values) {
                result &= !isEmpty(value);
            }
        }
        return result;
    }

    public static boolean isEmpty(String str) {
        return ((str == null) || (str.length() == 0));
    }

}
