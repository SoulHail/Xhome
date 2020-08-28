package com.codebear.xhome.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@Slf4j
@CacheConfig(cacheNames = "user") // 公共调用的缓存配置,eg:cacheNames;此次定义的属性，下面就可以不用定义；
@RestController
public class HelloController {

    /**
     * @Value： 获取配置文件中指定的某个值，application.properties优先级最高
     */
    @Value("${person.name}")
    private String value;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @RequestMapping("/hello")
    public String hello() {
        System.out.println(value);
        return "Hello Xhome!";
    }

    @RequestMapping("/level1/hello")
    public String helloStep() {
        return "Hello VIP!";
    }

    /**
     * 注：一般都是将方法入参和方法返回结果进行绑定，来进行缓存；用 @CachePut注解时需要注；
     * @Cacheable： 将方法的运行结果进行缓存，以后要相同的数据，直接从缓存中获取，不用调用方法；
     *      几个属性：
     *      cacheNames/value ：指定缓存组件的名字；是数组的形式，可以指定多个缓存；
     *      key： 缓存数据使用的key，可以用它来指定，默认是使用方法参数的值；
     *      condition： 符合某种条件才进行缓存；"#a0>1":第一个参数值》1时才进行缓存；
     *      sync： 是否异步；
     *
     * @CachePut: 既调用方法，又更新缓存数据；
     * @CacheEvict: 缓存清除，默认在方法执行之后执行；
     *      若清除方法执行过程出现异常，缓存就不会被清除；
     *      allEntries = true === 全清空；
     *      beforeInvocation = true === 在方法执行前清空缓存，防止出异常导致缓存未被清除；
     *
     * @Caching: 为上面三个注解的组合注解，一包含三,定义负责的缓存规则；
     * @return
     */
    @Cacheable(cacheNames = {"user"})
    //@CachePut(cacheNames = {"user"},key = "#result.id") //将key更新为返回结果的id，与返回结果进行绑定
    //@CacheEvict(cacheNames = {"user"},/*key = "#id"*/allEntries = true,beforeInvocation = true) //删除user缓存池中指定key的数据;
    /*@Caching(
            cacheable = {
                    @Cacheable(cacheNames = {"user"})
            },
            put = {
                    @CachePut(cacheNames = {"user"},key = "#result.id")
            }
    )*/
    @RequestMapping("query")
    public Map<String,Object> query() {
        log.info("=============================");
        List<Map<String,Object>> list = jdbcTemplate.queryForList("select * from at_user");
        return list.get(0);
    }

}
