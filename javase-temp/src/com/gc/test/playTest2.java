package com.gc.test;

import com.gc.factory.playFactory2;

public class playTest2 {
    public static void main(String[] args) {

        //多个工厂方法模式
        playFactory2 factory = new playFactory2();
        factory.produceComputer();

    }
}
