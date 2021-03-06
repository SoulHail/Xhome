package com.codebear.xhome.controller;

import com.codebear.xhome.entity.KqUser;
import com.codebear.xhome.mapper.KqUserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * 示例Controller
 *
 * @CacheConfig： 公共调用的缓存配置, eg:cacheNames;此次定义的属性，下面就可以不用定义；默认使用userRedisTemplate这个缓存管理器
 */
@Slf4j
@CacheConfig(cacheNames = "user", cacheManager = "userRedisTemplate")
@RestController
public class HelloController {

    @Autowired
    private KqUserMapper kqUserMapper;

    @Autowired
    JdbcTemplate jdbcTemplate;

    /**
     * @Value： 获取配置文件中指定的某个值，application.properties优先级最高
     */
    @Value("${person.name}")
    private String value;

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
     *
     * @return
     * @Cacheable： 将方法的运行结果进行缓存，以后要相同的数据，直接从缓存中获取，不用调用方法；
     * 几个属性：
     * cacheNames/value ：指定缓存组件的名字；是数组的形式，可以指定多个缓存；
     * key： 缓存数据使用的key，可以用它来指定，默认是使用方法参数的值；
     * condition： 符合某种条件才进行缓存；"#a0>1":第一个参数值》1时才进行缓存；
     * sync： 是否异步；
     * @CachePut: 既调用方法，又更新缓存数据；
     * @CacheEvict: 缓存清除，默认在方法执行之后执行；
     * 若清除方法执行过程出现异常，缓存就不会被清除；
     * allEntries = true === 全清空；
     * beforeInvocation = true === 在方法执行前清空缓存，防止出异常导致缓存未被清除；
     * @Caching: 为上面三个注解的组合注解，一包含三,定义负责的缓存规则；
     */
    @Cacheable(cacheNames = {"user"},cacheManager = "kqUserCacheManager")
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
    public Map<String, Object> query() {
        log.info("=============================");
        List<Map<String, Object>> list = jdbcTemplate.queryForList("select * from at_user");
        return list.get(0);
    }

    /**
     * 实际使用：
     * 以此为例，针对某个查询方法，加上@Cacheable注解，配置好对应的缓存管理器，即可将查询到的结果存入Redis中，下次再查询同样的数据时，就不会再访问数据库，而去缓存中取值
     * @param id
     * @return
     */
    @Cacheable(cacheNames = {"kquser"},cacheManager = "kqUserCacheManager")
    @RequestMapping("query/{id}")
    public KqUser queryId(@PathVariable("id")String id) {
        log.info("===============查询开始==============");
        return kqUserMapper.getKqUser(id);
    }

}
