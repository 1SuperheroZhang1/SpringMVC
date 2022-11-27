package com.atguigu.mvc.controller;

import com.atguigu.mvc.bean.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.net.http.HttpResponse;
import java.util.Arrays;

@Controller
public class ParamController {



    @RequestMapping("/testServletAPI")
    //形参位置的request表示当前请求
    public String testServletAPI(HttpServletRequest request){
        //Session(存在于服务端)依赖于Cookie(存在于客户端)
        HttpSession session = request.getSession();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        System.out.println("username:"+username+",password:"+password);
        return "success";
    }

    @RequestMapping("/testParam")
    //控制器的形参和当前请求的请求参数名保持一致，自动赋值
    public String testParam(
            /**@RequestParam注解：将请求参数和控制器方法的形参创建映射关系
             * @RequestParam注解的属性：
             *      value:指定为形参赋值的请求参数的参数名
             *      required: 设置是否必须传输此请求参数，默认值为true
             *          若设置为true时，则当前请求必须传输value所指定的请求参数。
             *          若没有传输该请求参数，且没有设置defaultValue属性，则页面报错400：Required String Parameter 'xxx' is not present；
             *          若设置为false，则当前请求不是必须传输value所指定的请求参数，若没有传输，则注解所标识的形参的值为null
             *      defaultValue: 不管required属性值为true或false，当value所指定的请求参数没有传输或传输的值为""时，则使用默认值为形参赋值。
             * */
            @RequestParam(value="user_name",required = false,defaultValue = "hehe") String username,
            String password,
            String[] hobby,
            /**@RequestHeader注解：将请求头信息和控制器方法的形参创建映射关系
             * @RequestParam注解的属性：
             *      value：请求头名称
             *      required： 是否必须传输此请求参数
             *      defaultValue： 默认值
             * */
            @RequestHeader("Host") String host,
            /**@CookieValue注解：将cookie数据和控制器方法的形参创建映射关系
             * @CookieValue注解的属性：
             *      value：session变量名
             *      required： 是否必须传输此请求参数
             *      defaultValue： 默认值
             * */
            @CookieValue("JSESSIONID") String JSESSIONID
            ){
        //若请求参数中出现多个同名的请求参数，可以在控制器方法的形参位置设置字符串类型或字符串数组接收此形参
        //若使用字符串类型的形参，最终结果为请求参数的每一个值之间使用逗号拼接
//        System.out.println("username:"+username+",password:"+password+",hobby:"+hobby);//username:admin,password:abc123,hobby:a,b,c
        //若使用字符串数组，最终结果为数组中的每个数据
        System.out.println("username:"+username+",password:"+password+",hobby:"+ Arrays.toString(hobby));//username:admin,password:123456,hobby:[a, c]
        System.out.println("host:"+host);//host:localhost:8080
        System.out.println("JSESSIONID:"+JSESSIONID);//JSESSIONID:AF7B7EC659DEC1A2C2177FEF514A1365
        return "success";
    }

    @RequestMapping("/testPOJO")
    public String testPOJO(User user){
        System.out.println(user);//User{id=null, username='zhangsan', password='123456', sex='女', age=23, email='123@qq.com'}
        /*乱码问题：
        *   get请求的乱码：tomcat造成的，在servlet.xml中已经解决.
        *   <Connector port="8080" URIEncoding="UTF-8" protocol="HTTP/1.1"
        *     connectionTimeout="20000"
        *     redirectPort="8443" />
        *   post请求的乱码： 通过配置Filter过滤器解决
        * */
        return "success";
    }
}
