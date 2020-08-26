package com.codebear.xhome.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@Slf4j
public class HelloController {

    /**
     * @Value： 获取配置文件中指定的某个值，application.properties优先级最高
     */
    @Value("${person.name}")
    private String value;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @RequestMapping("/hello")
    public String hello() {
        System.out.println(value);
        return "Hello Xhome!";
    }

    @RequestMapping("query")
    public Map<String,Object> query() {
        log.info("=============================");
        List<Map<String,Object>> list = jdbcTemplate.queryForList("select * from at_user");
        return list.get(0);
    }

}
