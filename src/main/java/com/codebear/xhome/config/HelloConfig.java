package com.codebear.xhome.config;

import com.codebear.xhome.service.HelloService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Configuration： 指明当前类是一个配置类，替代之前的Spring配置文件（beans.xml）
 * 之前在配置文件中用<bean></bean>标签添加组件，在配置类中，用@Bean注解来实现
 */
@Configuration
public class HelloConfig {

    /**
     * @Bean： 将方法的返回值添加到容器中，容器中默认的id就是方法名
     * @return
     */
    @Bean
    public HelloService helloService() {
        return new HelloService();
    }
}
