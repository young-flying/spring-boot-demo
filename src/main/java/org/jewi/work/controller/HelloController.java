package org.jewi.work.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {
	@Value("${com.test.name}")
	private String name;
	@Value("${com.test.value}")
	private String value;
	
    @RequestMapping("/hello")
    @ResponseBody
    String home( HttpServletRequest request) {
    	System.err.println(request.getCharacterEncoding());
    	System.out.println(name +":"+value);
    	System.out.println("这是测试的哦");
        return "Hello ,spring boot!这是测试的哦";
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(HelloController.class, args);
        //启动地址 http://localhost:8080/hello
    }
    
}