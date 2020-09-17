package com.codebear.xhome;

import com.codebear.xhome.bean.Person;
import com.codebear.xhome.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.sql.DataSource;
import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * 单元测试
 */
@SpringBootTest
@Slf4j
class XhomeApplicationTests {

    @Autowired
    private Person person;

    @Autowired
    private ApplicationContext ioc;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private AmqpAdmin amqpAdmin;

    @Autowired
    private JavaMailSenderImpl mailSender;

    @Autowired
    private StringRedisTemplate stringRedisTemplate; // 操作k-v都是字符串的

    @Autowired
    private RedisTemplate redisTemplate; // k-v不限

    @Autowired
    private RedisTemplate<Object, User> userRedisTemplate;

    /**********************************************************************************************************/

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

    /**********************************************************************************************************/

    /**
     * 测试系统数据源
     */
    @Test
    public void testData() throws SQLException {
        System.out.println(dataSource.getClass());
        Connection connection = dataSource.getConnection();
        System.out.println(connection);
        connection.close();
    }

    /**********************************************************************************************************/

    /**
     * 测试打印获取配置文件的属性值
     */
    @Test
    public void getPropertiesValue() {
        System.out.println(person);
    }

    /**********************************************************************************************************/

    /**
     * 测试配置类是否添加到Sprig容器中
     */
    @Test
    public void testHelloService() {
        boolean flag = ioc.containsBean("helloService");
        System.out.println(flag);
    }

    /**********************************************************************************************************/

    /**
     * 测试消息队列
     * 1、单播（点对点）
     * 2、广播
     * <p>
     * 跨系统接收消费信息，见RabbitMqService
     */
    @Test
    public void testRabbitMqSend() {
        // 发送消息的几种方式，常用到的方法

        //message需要自己构造一个，定义消息体内容和消息头
        //rabbitTemplate.send("exchange","routeKey",message);

        //object默认当成消息体，只需要传入要发送的对象，自动序列化发送给rabbitmq
        //rabbitTemplate.convertAndSend("exchange","routeKey",object);

        //广播模式，不需要路由键routeKey
        //rabbitTemplate.convertAndSend("exchange","",object);

    }

    /**
     * 接收队列消息,如何将数据自动转为json发送出去，见RabbitMqConfig类
     */
    @Test
    public void testRabbitMqReceive() {
        Object o = rabbitTemplate.receiveAndConvert("queneName");
        System.out.println(o.getClass());
        System.out.println(o);
    }

    /**
     * 代码创建Exchange、queue
     */
    @Test
    public void createExchange() {
        // 创建转换器
        amqpAdmin.declareExchange(new DirectExchange("amqpadmin.exchange"));

        // 创建队列，durable 是否是持久化
        amqpAdmin.declareQueue(new Queue("队列名称", true));

        // 创建绑定规则，将Exchange和queue绑定起来
        amqpAdmin.declareBinding(new Binding("队列名称", Binding.DestinationType.QUEUE, "交换器名称", "路由键,eg：amqp.haha", null));

        // 删除操作,只需要传名字即可
        amqpAdmin.deleteQueue("");
        amqpAdmin.deleteExchange("");
    }

    /**********************************************************************************************************/

    /**
     * 发送简单邮件测试
     */
    @Test
    public void setMailSender() {
        // 创建一个简单邮件
        SimpleMailMessage message = new SimpleMailMessage();
        // 邮件设置
        message.setSubject("通知-今晚开会"); // 标题
        message.setText("今晚8：30开会"); // 内容
        message.setTo("aaa@qq.com", "bbb@qq.com"); // 给谁发,可写多个地址，不限于QQ邮箱
        message.setFrom("ccc@qq.com"); // 邮件是谁发的
        mailSender.send(message);
    }

    /**
     * 发送复杂邮件测试
     */
    @Test
    public void setHardMailSender() throws MessagingException {
        // 创建一个复杂的消息邮件
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

        // 邮件设置
        helper.setSubject("通知-今晚开会"); // 标题
        helper.setText("<b style='color:red'>今晚9：30开会</b>", true); // 内容,可设置样式，样式生效，需把html属性设置为true
        helper.setTo("aaa@qq.com");
        helper.setFrom("ccc@qq.com"); // 邮件是谁发的

        // 上传文件,可传多个文件；文件名，文件路径
        helper.addAttachment("1.jpg", new File("E:\\idea背景\\1.jpg"));
        helper.addAttachment("2.jpg", new File("E:\\idea背景\\2.jpg"));
        mailSender.send(mimeMessage);
    }

    /**********************************************************************************************************/

    /**
     * redis测试--针对字符串对象
     * <p>
     * Redis常见的操作五大数据类型
     * String、List、Set、Hash、ZSet(有序集合)
     * stringRedisTemplate.opsForValue()--String
     * stringRedisTemplate.opsForList()--List
     * stringRedisTemplate.opsForSet()--Set
     * stringRedisTemplate.opsForHash()--Hash
     * stringRedisTemplate.opsForZSet()--ZSet
     */
    @Test
    public void redisTest() {
        /**
         * 数据添加
         */
        // 给redis保存String数据
        stringRedisTemplate.opsForValue().append("msg", "hello");

        // 给redis保存list数组
        stringRedisTemplate.opsForList().leftPush("mylist", "1");
        stringRedisTemplate.opsForList().leftPush("mylist", "2");
        stringRedisTemplate.opsForList().leftPush("mylist", "3");
        stringRedisTemplate.opsForList().leftPush("mylist", "4");
        stringRedisTemplate.opsForList().leftPush("mylist", "5");

        // 给redis保存Set数组
        stringRedisTemplate.opsForSet().add("set", "zhangsan");
        stringRedisTemplate.opsForSet().add("set", "lisi");

        /**
         * 数据获取
         */
        // 读取redis存储的String数据
        String a = stringRedisTemplate.opsForValue().get("msg");
        System.out.println(a);

        // 读取redis存储的List数据
        String b = stringRedisTemplate.opsForList().leftPop("mylist");
        String c = stringRedisTemplate.opsForList().rightPop("mylist");
        System.out.println(b);
        System.out.println(c);

        // 读取redis存储的Set数据
        String d = stringRedisTemplate.opsForSet().pop("set");
        System.out.println(d);
    }

    /**
     * redis测试--针对对象
     * redisTemplate同样也有操作这几种类型的方法
     * 针对对象操作,被操作对象需要实现Serializable接口
     */
    @Test
    public void redisObjTest() {
        /**
         * 数据添加
         */
        User user = User.builder()
                .id(3)
                .name("张三")
                .email("38472874@qq.com")
                .build();
        // 默认如果保存对象，使用jdk序列化机制，序列化后的数据保存到redis中
        redisTemplate.opsForValue().set("user", user);

        // 使用配置类修改后的json序列化机制存储数据
        userRedisTemplate.opsForValue().set("user", user);
    }

    @Test
    void contextLoads() {
    }

}
