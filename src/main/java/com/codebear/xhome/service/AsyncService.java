package com.codebear.xhome.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class AsyncService {

    /**
     * 告诉spring这是一个异步方法，生效需在主启动类上加入@EnableAsync注解
     */
    @Async
    public void async() {
        System.out.println("================执行异步方法===========");
    }

}
