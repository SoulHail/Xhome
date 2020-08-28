package com.codebear.xhome;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ImportResource;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * 主启动类
 * @ImportResource： 项目启动时，除全局配置外，额外加载指定的配置文件
 * 但在SpringBoot中，常用配置类来取代其他的一些配置文件
 *
 * @EnableCaching: 开启缓存功能
 */
@Slf4j
@SpringBootApplication
@EnableCaching // 开启基于注解的cache缓存模式
//@EnableAsync // 开启异步注解功能
//@EnableRabbit //开启基于注解的RabbitMq模式
//@ImportResource(locations = {"classpath:beans.xml"})
public class XhomeApplication {

    public static void main(String[] args) {
        SpringApplication.run(XhomeApplication.class, args);
        log.info(print());
    }

    private static String print() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n");
        sb.append("                   _ooOoo_\n");
        sb.append("                  o8888888o\n");
        sb.append("                  88\" . \"88\n");
        sb.append("                  (| -_- |)\n");
        sb.append("                  O\\  =  /O\n");
        sb.append("               ____/`---'\\____\n");
        sb.append("             .'  \\\\|     |//  `.\n");
        sb.append("            /  \\\\|||  :  |||//  \\ \n");
        sb.append("           /  _||||| -:- |||||-  \\ \n");
        sb.append("           |   | \\\\\\  -  /// |   |\n");
        sb.append("           | \\_|  ''\\---/''  |   |\n");
        sb.append("           \\  .-\\__  `-`  ___/-. /\n");
        sb.append("         ___`. .'  /--.--\\  `. . __\n");
        sb.append("      .\"\" '<  `.___\\_<|>_/___.'  >'\"\".\n");
        sb.append("     | | :  `- \\`.;`\\ _ /`;.`/ - ` : | |\n");
        sb.append("     \\  \\ `-.   \\_ __\\ /__ _/   .-` /  /\n");
        sb.append("======`-.____`-.___\\_____/___.-`____.-'======\n");
        sb.append("                   `=---='\n");
        sb.append("...................................................\n");
        return sb.toString();
    }

}
