package com.mp.pojo;

import com.mp.inte.China;

public class Person  implements China {
    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public Person() {
    }

    @Override
    public String toString(){ return "["+this.name+" "+this.age+"]"; }


    @Override
    public void sayChina() {
        System.out.println("hello ,china");
    }

    @Override
    public void sayHello(String name, int age) {
        System.out.println(name+" "+age);
    }
}
