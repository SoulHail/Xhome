package com.codebear.xhome.bean;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 将配置文件中配置的每一个属性的值，映射到这个组件中
 * @Component: 将此类放入Spring容器中，使其在初始化是能够被扫描到
 * @ConfigurationProperties: 告诉SpringBoot将本类中的所有属性和配置文件中相关的配置进行绑定,默认从全局配置文件获取值
 * prefix = "person" ： 配置文件中的那一块配置进行绑定
 * 只有这个组件是容器中的组件，才能用@ConfigurationProperties功能
 *
 * @PropertySource: 加载指定配置文件
 */
@Data
@Component
@ConfigurationProperties(prefix = "person")
@PropertySource(value = {"classpath:person.properties"})
public class Person {

    private String name;
    private Integer age;
    private Boolean boss;
    private Date birth;

    private Map<String,Object> maps;
    private List<Object> lists;
}
