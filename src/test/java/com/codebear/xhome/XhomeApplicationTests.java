package com.codebear.xhome;

import com.codebear.xhome.bean.Person;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

/**
 * 单元测试
 */
@SpringBootTest
@Slf4j
class XhomeApplicationTests {

    @Autowired
    Person person;

    @Autowired
    ApplicationContext ioc;

    /**
     * 测试日记信息打印
     */
    @Test
    public void testLog() {
        log.trace("这是trace级别日志信息。。。");
        log.debug("这是debug级别日志信息。。。");
        log.info("这是info级别日志信息。。。");
        log.warn("这是warn级别日志信息。。。");
        log.error("这是error级别日志信息。。。");
    }

    /**
     * 测试打印获取配置文件的属性值
     */
    @Test
    public void getPropertiesValue() {
        System.out.println(person);
    }

    /**
     * 测试配置类是否添加到Sprig容器中
     */
    @Test
    public void testHelloService() {
        boolean flag = ioc.containsBean("helloService");
        System.out.println(flag);
    }

    @Test
    void contextLoads() {
    }

}
