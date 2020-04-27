package com.mp.controller;

import com.mp.entity.EnumType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class PageController {
    @RequestMapping("/toLogin")
    public String toLogin() {

        return "login";
    }
    @RequestMapping("/main")
    public String main() {

        return "main";
    }


    @RequestMapping("/test")
    public String test() {

        EnumType enumType = new EnumType();
      //  enumType.setStateDesc(enumType.getStateDesc());
        return "main";
    }
}
