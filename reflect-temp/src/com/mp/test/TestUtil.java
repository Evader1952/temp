package com.mp.test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public  class TestUtil {

    //反射调用构造方法
    public  static void test1() throws ClassNotFoundException {

//        User user = new User();
//        Class<? extends User> userClass = user.getClass();
        Class<?> userClass = Class.forName("com.mp.pojo.User");
        Constructor<?>[] declaredConstructors = userClass.getDeclaredConstructors();
        System.out.println(declaredConstructors.length);

    }
    //反射调用属性
    public  static void test2() throws ClassNotFoundException, NoSuchFieldException, IllegalAccessException, InstantiationException {


        //通过反射得到类
        Class<?> userClass = Class.forName("com.mp.pojo.User");
        //得到name属性
        Field name = userClass.getDeclaredField("name");
        name.setAccessible(true);
        //实例化
        Object obj = userClass.newInstance();
        name.set(obj, "mp");
        Object o = name.get(obj);
        System.out.println(o);

    }
    //反射调用方法
    public  static void test3() throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException {

        Class<?> userClass = Class.forName("com.mp.pojo.User");

        Method sayHello = userClass.getDeclaredMethod("sayHello", String.class);
        Object obj = userClass.newInstance();
        Object mp = sayHello.invoke(obj, "mp");
        System.out.println(mp);

    }


}
