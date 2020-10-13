package com.codebear.xhome.config;

import org.redisson.Redisson;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 初始化一个Redis客户端
 */
@Configuration
public class RedissonConfig {

    @Bean
    public Redisson redisson() {
        // 此为单机模式
        Config config = new Config();
        config.useSingleServer().setAddress("redis地址").setDatabase(0);
        return (Redisson) Redisson.create(config);
    }
}
