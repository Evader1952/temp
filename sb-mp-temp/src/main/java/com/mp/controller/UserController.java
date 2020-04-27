package com.mp.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {
    @PostMapping("/login")
    public String login(String name,String password,Model model) {

        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(name, password);
        try {
            subject.login(token);
            return "main";
        }catch (UnknownAccountException e){
        model.addAttribute("msg", "用户名错误");
        }catch (IncorrectCredentialsException e){
            model.addAttribute("msg", "密码错误");
        }
        return null;
    }
}
