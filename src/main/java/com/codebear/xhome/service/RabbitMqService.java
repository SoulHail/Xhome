package com.codebear.xhome.service;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class RabbitMqService {

//    /**
//     * 监听要起作用，必须在启动类上加入@EnableRabbit注解，开启基于注解的RabbitMq模式
//     */
//    @RabbitListener(queues = "队列名称")
//    public void receive() {
//        // 只有该队列有消息进来，就会获取到消息内容
//        System.out.println("收到消息：");
//    }
//
//    @RabbitListener(queues = "队列名称")
//    public void receiveMessage(Message message) {
//        // 接收消息头信息
//        System.out.println(Arrays.toString(message.getBody()));
//        System.out.println(message.getMessageProperties());
//    }
}
