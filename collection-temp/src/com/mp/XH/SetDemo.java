package com.mp.XH;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

public class SetDemo {
    public static void main(String[] args) {
        Set<Integer> test = new TreeSet<>();
        test.add(1);
        test.add(2);
        test.add(5);
        test.add(4);
        //1.遍历集合test   利用foreach遍历
        for (Integer value : test) {
            System.out.print(value+" ");
        }

       //2.利用Iterator实现遍历
        Iterator<Integer> value = test.iterator();
        while (value.hasNext()) {
            int s = value.next();
            System.out.print(s+" ");
        }
    }
}
