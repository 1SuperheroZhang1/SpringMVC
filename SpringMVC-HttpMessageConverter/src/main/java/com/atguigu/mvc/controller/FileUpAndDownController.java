package com.atguigu.mvc.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

@Controller
public class FileUpAndDownController {

    /**
     * ResponseEntity：自定义响应报文，响应到浏览器
     * */
    @RequestMapping("/testDownload")
    public ResponseEntity<byte[]> testResponseEntity(HttpSession session) throws IOException {
        //获取ServletContext对象
        ServletContext servletContext = session.getServletContext();
        //获取服务器中文件的真实路径
        String realPath = servletContext.getRealPath("/static/img/1.jpg");
        //创建输入流
        InputStream is = new FileInputStream(realPath);
        //创建字节数组 -is.available()获取当前输入流所对应文件的所有字节数
        //available():获取输入流所对应的文件的字节数
        byte[] bytes = new byte[is.available()];
        //将流读到字节数组中
        is.read(bytes);
        //创建HttpHeaders对象设置响应头信息
        MultiValueMap<String, String> headers = new HttpHeaders();
        //设置要下载方式以及下载文件的文件名
        headers.add("Content-Disposition", "attachment;filename=1.jpg");
        //设置响应状态码
        HttpStatus statusCode = HttpStatus.OK;
        //创建ResponseEntity对象
        ResponseEntity<byte[]> responseEntity = new ResponseEntity<byte[]>(bytes, headers, statusCode);
        //关闭输入流
        is.close();
        return responseEntity;
    }

    @RequestMapping("/testUpload")
    public String testUpload(MultipartFile photo,HttpSession session) throws IOException {
        //获取上传的文件的文件名
        String fileName = photo.getOriginalFilename();
        //处理文件重名问题
        //获取上传的文件的后缀名
        String suffixName=fileName.substring(fileName.lastIndexOf("."));
        //将UUID作为文件名
        String uuid= UUID.randomUUID().toString();
        //将uuid和后缀名拼接后的结果作为最终的文件名
        fileName=uuid+suffixName;
        //通过ServletContext获取服务器中photo目录的路径
        ServletContext servletContext = session.getServletContext();
        String photoPath = servletContext.getRealPath("photo");
        File file=new File(photoPath);
        //判断photoPath路径是否存在
        if(!file.exists()){
            //若不存在，则创建目录
            file.mkdir();
        }
        String finalPath=photoPath+File.separator+fileName;
        //transferTo():资源转移。将浏览器上传的文件转移到服务器中
        //实现上传功能
        photo.transferTo(new File(finalPath));
        return "success";
    }
}
