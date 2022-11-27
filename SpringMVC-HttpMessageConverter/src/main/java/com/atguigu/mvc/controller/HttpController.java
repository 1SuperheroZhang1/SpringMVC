package com.atguigu.mvc.controller;

import com.atguigu.mvc.bean.User;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**@RestController注解
 * @RestController注解是SpringMVC提供的一个复合注解，标识在控制器的类上。
 * 就相当于为类添加了@Controller注解，并且为其中的每个方法添加了@ResponseBody注解
 * */
@RestController
@Controller
public class HttpController {

    /** @RequestBody注解
    可以获取请求体，需要在控制器方法设置一个形参，使用@requestBody进行标识，
    当前请求的请求体就会为当前注解所标识的形参赋值。
    */
    @RequestMapping("/testRequestBody")
    public String testRequestBody(@RequestBody String requestBody){
        System.out.println("requestBody："+requestBody);
        return "success";
    }

    /** RequestEntity:封装请求报文的一种类型，需要在控制器方法的形参中设置该类型的形参，当前请求的请求报文就会赋值给该形参。
     * 通过getHeaders()获取请求头信息
     * 通过getBody()获取请求体信息
     * */
    @RequestMapping("/testRequestEntity")
    public String testRequestEntity(RequestEntity<String> requestEntity){
        //当前requestEntity表示整个请求报文的信息
        System.out.println("请求头："+requestEntity.getHeaders());
        System.out.println("请求体："+requestEntity.getBody());
        return "success";
    }

    /** 原生Servlet响应浏览器数据的方式：
              通过请求转发和请求重定向
              通过response.getWriter().write()或response.getWriter().print()
        SpringMVC响应浏览器数据的方式：

     * */
    //使用ServletAPI HttpServletResponse相应浏览器数据
    @RequestMapping("/testResponse")
    public void testResponse(HttpServletResponse response) throws IOException {
        response.getWriter().print("hello,response");
    }

    /** @ResponseBody注解
     * @ResponseBody用于标识一个控制器方法，可以将该方法的返回值直接作为响应报文的响应体响应到浏览器
     * */
    @RequestMapping("/testResponseBody")
    @ResponseBody
    public String testResponseBody(){
        return "hello,response";
    }


    @RequestMapping("/testResponseUser")
    @ResponseBody
    public User testResponseUser(){
        return new User(1001,"zhangsan","abc123",23,"male");
    }

    @RequestMapping("/testAxios")
    @ResponseBody
    public String testAxios(String username,String password){
        System.out.println("username="+username+",password="+password);
        return "hello,axios";
    }
}
