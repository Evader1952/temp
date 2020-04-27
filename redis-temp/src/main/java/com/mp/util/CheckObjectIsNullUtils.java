package com.mp.util;

import java.lang.reflect.Field;
import java.lang.reflect.Type;

/**
 * <p>Description: 判断对象是否为空，进一步判断对象中的属性是否都为空 </p>
 *
 * @author duanfeixia
 * @date 2018年11月12日
 */
public class CheckObjectIsNullUtils {

    /**
     * 判断对象是否为空，且对象的所有属性都为空
     * ps: boolean类型会有默认值false 判断结果不会为null 会影响判断结果
     * 序列化的默认值也会影响判断结果
     *
     * @param object
     * @return
     */
    public static void objCheckIsNull(Object object, StringBuffer str) {
        Class clazz = (Class) object.getClass(); // 得到类对象
        Field fields[] = clazz.getDeclaredFields(); // 得到所有属性
//        boolean flag = true; //定义返回结果，默认为true
        for (Field field : fields) {
            field.setAccessible(true);
            Object fieldValue = null;
            String fieldName = "";
            try {
                fieldValue = field.get(object); //得到属性值
                Type fieldType = field.getGenericType();//得到属性类型
                fieldName = field.getName(); // 得到属性名
              /*  System.out.println("属性类型：" + fieldType + ",属性名：" + fieldName + ",属性值：" + fieldValue);*/
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            if (fieldValue != null) {  //只要有一个属性值不为null 就返回false 表示对象不为null
//                flag = false;
                if (fieldValue.toString().length() <= 8 && !fieldName.toString().equals("hash") && str.indexOf(fieldName)==-1)
                    str.append("_"+fieldName + "_" + fieldValue);

            }
        }
//        return str;
//        return flag;
    }

}