package com.mp.pojo;

public class User {
    private String name;
    private String  tel;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public User(String name, String tel) {
        this.name = name;
        this.tel = tel;
    }

    public User() {

    }

    public void sayHello(String name) {

        System.out.println("hello："+name);
    }
}



