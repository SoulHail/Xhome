package com.codebear.xhome.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HelloController {

    /**
     * @Value： 获取配置文件中指定的某个值，application.properties优先级最高
     */
    @Value("${person.name}")
    private String value;

    @RequestMapping("/hello")
    public String hello() {
        System.out.println(value);
        return "Hello Xhome!";
    }

}
