package com.atguigu.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
//@RequestMapping("/hello")
/** @RequestMapping注解
 * 1.@RequestMapping注解的功能
 *      从注解名称可以看到，@RequestMapping注解的作用就是将请求和处理请求的控制器方法关联起来，建立映射关系。
 *      SpringMVC接收到指定的请求，就会来找到在映射关系中对应的控制方法来处理这个请求。
 *
 * 2.@RequestMapping注解的位置：
 *      @RequestMapping标识在类上面：设置映射请求的请求路径的初始信息 - 相当于文件夹
 *      @ReqeustMapping标识在方法上面：设置响应请求的请求路径的详细信息
 * */
public class RequestMappingController {

    //Error:多个控制器里面，不能有多个@RequestMapping注解的value属性值一样
//    @RequestMapping("/")
//    public String target(){
//        return "target";
//    }
    //此时请求映射所映射的请求的请求路径为：/hello/testRequestMapping
//    @RequestMapping("/testRequestMapping")
/**3.@RequestMapping注解的value属性
 *      @RequestMapping注解的value属性通过请求的请求地址匹配请求映射
 *      @RequestMapping注解的value属性是一个字符串类型的数组(String[])，表示该请求映射能够匹配多个请求地址所对应的请求
 *      @RequestMapping注解的value属性必须设置。至少通过请求地址匹配请求映射
 * 4.@RequestMapping注解的method属性
 *      @RequestMapping注解的method属性通过请求的请求方式(GET或POST)匹配请求映射
 *      @RequestMapping注解的method属性是一个RequestMethod类型的数组(RequestMethod[])，表示该请求映射能够匹配多种请求方式的请求
 *      若当前请求的请求地址满足请求映射的value属性，但是请求方式不满足method属性，则浏览器报错405:Request method 'POST' not supported
 * */
    @RequestMapping(
            value={"/testRequestMapping","/test"},
            method={RequestMethod.GET,RequestMethod.POST}
    )
    public String success(){
        //返回视图名称
        return "success";
    }

    /**
     * 1.对于处理指定请求方式的控制器方法，SpringMVC中提供了@RequestMapping的派生注解
     *     处理get请求的映射    --> @GetMapping
     *     处理post请求的映射   --> @PostMapping
     *     处理put请求的映射    --> @PutMapping
     *     处理delete请求的映射 --> @DeleteMapping
     * 2.常见的请求处理方式有：get、post、put、delete，目前浏览器只支持get和post
     *   若在form表单提交时，为method设置了其他请求方式的字符串(put或delete)，则按照默认的请求方式get处理
     *   若要发送put和delete请求，则需要通过spring提供的过滤器HiddenHttpMethodFilter(RESTful部分学习)
     * */
    @GetMapping("/testGetMapping")
    public String testGetMapping(){
        return "success";
    }

    @RequestMapping(value = "/testPut",method = RequestMethod.PUT)
    public String testPut(){
        return "success";
    }

/** 5.@RequestMapping注解的params属性
 *      @RequestMapping注解的params属性通过请求的请求参数匹配请求映射
 *      @RequestMapping注解的params属性是一个字符串类型的数组，可以通过四种表达式设置请求参数和请求映射的匹配关系
 *          "param": 要求请求映射所匹配的请求必须携带param请求参数
 *          "!param": 要求请求映射所匹配的请求必须不能携带param请求参数
 *          "param=value": 要求请求映射所匹配的请求必须携带param请求参数且param=value
 *          "param!=value": 要求请求映射所匹配的请求必须携带param请求参数但是param!=value
 *      若当前请求满足@RequestMapping注解的value和method属性，但是不满足params属性，此时页面会报错400
 *  6.@RequestMapping注解的headers属性
 *      @RequestMapping注解的headers属性是通过请求的请求头信息匹配请求映射
 *      @RequestMapping注解的headers属性是一个字符串类型的数组，可以通过四种表达式设置请求头信息和请求映射的匹配关系。
 *          "header": 要求请求映射所匹配的请求必须携带header请求头信息
 *          "!header": 要求请求映射所匹配的请求必须不能携带header请求头信息
 *          "header=value": 要求请求映射所匹配的请求必须携带header请求头信息且header=value
 *          "header!=value": 要求请求映射所匹配的请求必须携带header请求头信息且header!=value
 *     若当前请求满足@RequestMapping注解的value属性和method属性，但是不满足headers属性，此时页面显示404错误，即资源未找到
 * */
    @RequestMapping(
            value="/testParamsAndHeaders",
            params = {"username","password!=123456"},
            headers = {"Host=localhost:8080"}
    )
    public String testParamsAndHeaders(){
        return "success";
    }

/** 7.SpringMVC支持Ant风格(模糊匹配)的路径
 *      ?: 表示任意的单个字符
 *      *: 表示任意的0个或多个字符
 *      **: 表示任意的一层或多层目录
 *   注意：在使用**时，只能使用/**(\)/xxx的方式
 * */
//    @RequestMapping("/a?a/testAnt")
    @RequestMapping("/a*a/testAnt")
//    @RequestMapping("/**/testAnt")
    public String testAnt(){
        return "success";
    }
/** 8.SpringMVC支持路径中的占位符(重点)
 *      原始方式：/deleteUser?id=1
 *      rest方式：/deleteUser/1
 *   SpringMVC路径中的占位符常用于RESTful风格中
 *   当请求路径中将某些数据通过路径的方式传输到服务器中，就可以在相应的@RequestMapping注解的value属性中通过占位符{xxx}表示传输的数据，
 *   再通过@PathVariable注解，将占位符所表示的数据赋值给控制器方法的形参。
 *
 * */
    @RequestMapping("/testPath/{id}/{username}")
    public String testPath(@PathVariable("id") Integer id,@PathVariable("username") String username){
        System.out.println("id:"+id+","+"username:"+username);
        return "success";
    }
}
