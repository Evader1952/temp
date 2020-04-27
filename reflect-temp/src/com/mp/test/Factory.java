package com.mp.test;

import com.mp.inte.fruit;

public class Factory {
    public static fruit getInstance(String ClassName) throws Exception {
        fruit f=null;
        f=(fruit)Class.forName(ClassName).newInstance();
        return f;
    }
}
