package com.atguigu.mvc.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

//@ControllerAdvice将当前类标识为异常处理的组件
@ControllerAdvice //@ControllerAdvice组件的扩展注解
public class ExceptionController {

    //@ExceptionHandler用于设置所标识方法处理的异常
    @ExceptionHandler(value={ArithmeticException.class,NullPointerException.class})
    public String testException(Exception ex, Model model){ //ex表示当前请求处理中出现的异常对象
        model.addAttribute("ex",ex);
        return "error";
    }
}
