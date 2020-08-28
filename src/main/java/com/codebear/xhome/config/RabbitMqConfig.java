package com.codebear.xhome.config;

import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * RabbitMq配置类
 */
@Configuration
public class RabbitMqConfig {

//    @Bean
//    public MessageConverter messageConverter() {
//        // 重新设置RabbitMq存储数据时的序列化方式
//        return new Jackson2JsonMessageConverter();
//    }
}
