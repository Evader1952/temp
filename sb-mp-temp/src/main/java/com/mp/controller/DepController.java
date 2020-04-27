package com.mp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class DepController {

    @GetMapping("/index")
   // @ResponseBody
    public String xx() {

        return "index";
    }

    @RequestMapping("/toAdd")
    public String toAdd( ){

        return  "add";
    }

    @RequestMapping("/toUpdate")
    public String toUpdate( ){

        return  "update";
    }
}
