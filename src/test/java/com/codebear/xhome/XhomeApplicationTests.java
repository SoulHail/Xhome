package com.codebear.xhome;

import com.codebear.xhome.bean.Person;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

/**
 * 单元测试
 */
@SpringBootTest
class XhomeApplicationTests {

    @Autowired
    Person person;

    @Autowired
    ApplicationContext ioc;

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
