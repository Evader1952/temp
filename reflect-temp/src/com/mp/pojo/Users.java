package com.mp.pojo;

public class Users {
    private static String name = "zhangsan";
    private Users(String name) { this.name = name; }
    private static Users user = new Users(name);
    public static Users getInstance() { return user; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}
