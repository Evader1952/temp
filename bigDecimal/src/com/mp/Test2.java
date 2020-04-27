package com.mp;

import com.mp.entity.User;

public class Test2 {

    public static void main(String[] args) {
        User user = new User();
        Integer code = User.Type.MANAGE.getCode();
        System.out.println(code);


    }
}
