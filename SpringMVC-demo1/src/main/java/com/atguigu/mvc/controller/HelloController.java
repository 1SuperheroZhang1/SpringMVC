package com.atguigu.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
    创建请求控制器
*/
@Controller
public class HelloController {

    // "/" -->/WEB-INF/templates/index.html

    /**
     * @RequestMapping注解：处理请求和控制器方法之间的映射关系
     * @RequestMapping注解的value属性可以通过请求地址匹配请求，
     * /表示当前工程的上下文路径 localhost:8080/SpringMVC_demo1/
     * */
    @RequestMapping(value="/")
    public String index(){
        //返回试图名称
        return "index";
    }

    @RequestMapping("/target")
    public String toTarget(){
        return "target";
    }
}
