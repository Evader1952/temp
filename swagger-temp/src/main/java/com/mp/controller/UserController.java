package com.mp.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class UserController {
    //记录器
//    private  final Log log = LogFactory.getLog(UserController.class);
    @GetMapping("/add")
    public String add(String str) {

        return str;
    }

    @PostMapping("/find")
    public String find(String str) {
        return str;
    }

    @RequestMapping("/log")
    public String log() {
//日志的级别；
//由低到高 trace<debug<info<warn<error
//可以调整输出的日志级别；日志就只会在这个级别以以后的高级别生效
        log.trace("trace级别的日志");
        log.debug("debug级别的日志");
//SpringBoot默认给我们使用的是info级别的，没有指定级别的就用SpringBoot默认规定的级别；root级别
        log.info("info级别的日志");
        log.warn("warn级别的日志");
        log.error("error级别的日志");
    return "logTest";
    }
}
