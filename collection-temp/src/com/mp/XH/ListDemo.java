package com.mp.XH;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ListDemo {
    public static void main(String[] args) {
        //List的五种遍历方法
        List<String> list=new ArrayList<String>();
        list.add("AAAA");
        list.add("BBBB");
        list.add("CCCC");

        //1.foreach遍历
        for (String s:list) {
            System.out.println(s);
        }
        //2.调用集合迭代器

        for (  Iterator<String> it=list.iterator();it.hasNext();){
            System.out.println(it.next());
        }
//        Iterator<String> it=list.iterator();
//        while (it.hasNext()){
//            System.out.println(it.next());
//        }


        //3.for循环，下标递增
        for (int i = 0; i <list.size() ; i++) {
            System.out.println(list.get(i));
        }


    }
}
