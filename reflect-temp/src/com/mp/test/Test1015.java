package com.mp.test;

import com.mp.inte.fruit;
import com.mp.pojo.Person;
import com.mp.pojo.User;
import com.mp.pojo.Users;

import java.lang.reflect.*;

public class Test1015 {

    //【案例 1】反射获得对象完整的包名和类名
    public  static void test1(){
        User user = new User();
        System.out.println(user.getClass().getName());
    }

    //【案例 2】实例化 Class 类对象
    public  static void test2() throws Exception {
        User user = new User();
        Class<?> demo1=null;
        Class<?> demo2=null;
        Class<?> demo3=null;
        demo1=Class.forName("com.mp.pojo.User");//一般尽量采用这种形式
        demo2=new User().getClass();
        demo3=User.class;
        System.out.println("类名称 "+demo1.getName());
        System.out.println("类名称 "+demo2.getName());
        System.out.println("类名称 "+demo3.getName());
    }

    //【案例 3】反射给对象赋值
    public  static void test3() throws Exception {
        Class<?> demo =Class.forName("com.mp.pojo.Person");
        Person per=(Person)demo.newInstance();
        per.setName("MP");
        per.setAge(20);
        System.out.println(per);

    }
    //【案例 4】反射调用不同构造器创建对象
    public  static void test4() throws Exception {
        Class<?> demo=Class.forName("com.mp.pojo.Person");
        Constructor<?> cons[]=demo.getDeclaredConstructors();//取得全部的构造函数
        System.out.println(cons.length);
        Person per1=(Person)cons[0].newInstance();
        Person per2=(Person)cons[1].newInstance("BB",18);
//        Person per3=(Person)cons[2].newInstance(20);
//        Person per4=(Person)cons[3].newInstance("BB",20);
        System.out.println(per1);
        System.out.println(per2);
//        System.out.println(per3);
//        System.out.println(per4);
    }
    //反射获得接口名称
    public  static void test5() throws Exception {
        Class<?> demo=Class.forName("com.mp.pojo.Person");
        Class<?> intes[]=demo.getInterfaces();//保存所有的接口
        for (int i = 0; i < intes.length; i++) {
            System.out.println("实现的接口 "+intes[i].getName());
        }
    }
    //【案例 6】反射获得类中的父类
    public  static void test6() throws Exception {
        Class<?> demo=Class.forName("com.mp.pojo.Person");
            Class<?> temp=demo.getSuperclass();//取得父类
            System.out.println("继承的父类为： "+temp.getName());

    }
    //【案例 7】反射获得全部构造器
    public  static void test7() throws Exception {
        Class<?> demo=Class.forName("com.mp.pojo.Person");
        Constructor<?>cons[]=demo.getConstructors();
        for (int i = 0; i < cons.length; i++) {
            System.out.println("构造方法： "+cons[i]);
        }
    }
    //【案例 8】反射打印完整构造器
    public  static void test8() throws Exception {
        Class<?> demo=Class.forName("com.mp.pojo.Person");
        Constructor<?> cons[] = demo.getConstructors();
        for (int i = 0; i < cons.length; i++) {
            Class<?> p[] = cons[i].getParameterTypes();//参数类型
            System.out.print("构造方法： ");
            int mo=cons[i].getModifiers();//返回修饰符的整型标识
            System.out.print(Modifier.toString(mo)+" ");
            System.out.print(cons[i].getName());
            System.out.print("(");
            for(int j=0;j<p.length;++j){
                System.out.print(p[j].getName()+" arg"+i);
                if(j<p.length-1){
                    System.out.print(",");
                } }
            System.out.println("){}");
        }
    }
    //【案例 9】反射取得一个类的全部框架
    public  static void test9() throws Exception {
        Class<?> demo=Class.forName("com.mp.pojo.Person");
        System.out.println("===============本类属性=================");
        Field[] field = demo.getDeclaredFields();// 取得本类的全部属性
        for (int i = 0; i < field.length; i++) {
            int mo = field[i].getModifiers();// 权限修饰符
            String priv = Modifier.toString(mo);
            Class<?> type = field[i].getType();// 属性类型
            System.out.println(priv + " " + type.getName() + " "+ field[i].getName() + ";");
        }
        System.out.println("===========实现的接口或者父类的属性===========");
        Field[] filed1 = demo.getFields();// 取得实现的接口或者父类的属性
        for (int j = 0; j < filed1.length; j++) {
            int mo = filed1[j].getModifiers();// 权限修饰符
            String priv = Modifier.toString(mo);
            Class<?> type = filed1[j].getType();// 属性类型
            System.out.println(priv + " " + type.getName() + " " + filed1[j].getName() + ";");
        }
    }
    //【案例 10】反射调用其他类中的方法
    public  static void test10() throws Exception {
        Class<?> demo=Class.forName("com.mp.pojo.Person");
        Method method=demo.getMethod("sayChina");//调用 Person 类中的 sayChina 方法
        method.invoke(demo.newInstance());
        method=demo.getMethod("sayHello", String.class,int.class); //调用 Person 的 sayHello 方法
        method.invoke(demo.newInstance(),"MP",20);
    }
    //反射调用对象 set/get 方法
    /***@param obj 操作的对象; att 操作的属性***/
    public static void getter(Object obj, String att) throws Exception {
        Method method = obj.getClass().getMethod("get" + att);
        System.out.println(method.invoke(obj));
    }
    /** @param obj 操作的对象;att 操作的属性;value 设置的值;type 参数的属性***/
    public static void setter(Object obj, String att, Object value,Class<?> type)throws Exception {
        Method method = obj.getClass().getMethod("set" + att, type);
        method.invoke(obj, value);
    }
    public  static void test11() throws Exception {
        Class<?> demo=Class.forName("com.mp.pojo.Person");
        Object obj=demo.newInstance();
        setter(obj,"Name","mp",String.class);
        getter(obj,"Name");
    }

    //【案例 12】反射操作成员变量
    public  static void test12() throws Exception {
        Class<?> demo=Class.forName("com.mp.pojo.Person");
        Object obj = demo.newInstance();
        Field field = demo.getDeclaredField("name");
        field.setAccessible(true);
        field.set(obj, "mp");
        System.out.println(field.get(obj));
    }
    //【案例 13】反射修改数组元素
    public  static void test13() throws Exception {
        int[] temp={1,2,3,4,5};
        Class<?>demo=temp.getClass().getComponentType();
        System.out.println("数组类型： "+demo.getName());
        System.out.println("数组长度 "+Array.getLength(temp));
        System.out.println("数组的第一个元素: "+Array.get(temp, 0));
        Array.set(temp, 0, 100);
        System.out.println("修改之后数组第一个元素为： "+Array.get(temp, 0));

    }
    //【案例 14】反射修改数组长度
    /*** 修改数组大小***/
    public static Object arrayInc(Object obj,int lenth){
        Class<?>arr=obj.getClass().getComponentType();
        Object newArr=Array.newInstance(arr, lenth);
        int count=Array.getLength(obj);
        System.arraycopy(obj, 0, newArr, 0, count);
        return newArr; }
    /*** 打印 ***/
    public static void print(Object obj){
        Class<?>c=obj.getClass();
        if(!c.isArray()){
            return; }
        System.out.println("数组长度为： "+Array.getLength(obj));
        for (int i = 0; i < Array.getLength(obj); i++) {
            System.out.print(Array.get(obj, i)+" ");
        } }
    public  static void test14() throws Exception {

        int[] arr={1,2,3,4,5,6,7,8,9};
        int[] arrNew=(int[])arrayInc(arr,15);
        print(arrNew);
        System.out.println("=====================");
        String[] str={"a","b","c"};
        String[] str1=(String[])arrayInc(str,8);
        print(str1);

    }

    //【案例 15】反射创建单例对象
    public  static void test15() throws Exception {
        System.out.println(Users.getInstance());//普通单例模式打印
            //反射打印对象
        Constructor<Users> constructor = Users.class.getDeclaredConstructor(String.class); constructor.setAccessible(true);
        Users u = constructor.newInstance("lisi");
        System.out.println(u);
    }
    public  static void test16() throws Exception {
        fruit f=Factory.getInstance("com.mp.pojo.Apple");
        if(f!=null){ f.eat(); }
    }

}
