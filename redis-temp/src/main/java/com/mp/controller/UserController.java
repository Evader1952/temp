package com.mp.controller;

import com.mp.annotation.RedisCache;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RedisCache
@RestController

public class UserController {

    @RequestMapping("test")
    public  String  findAll(){

        return  "你是猪";
    }
}
