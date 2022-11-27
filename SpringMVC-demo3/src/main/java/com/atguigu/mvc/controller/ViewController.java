package com.atguigu.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ViewController {

    //ThymeleafView
    @RequestMapping("/testThymeleafView")
    public String  testThymeleafView(){
        return "success";
    }

    //转发视图InternalResourceView
    @RequestMapping("/testForward")
    public String testForward(){
        return "forward:/testThymeleafView";
    }

    //重定向试图RedirectView
    @RequestMapping("/testRedirect")
    public String testRedirect(){
        return "redirect:/testThymeleafView";
    }
}
