package com.codebear.xhome.config;

import com.codebear.xhome.entity.KqUser;
import com.codebear.xhome.entity.User;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;

import java.net.UnknownHostException;
import java.util.Objects;

/**
 * redis配置类
 * 注意：
 * 1、针对对不同对象进行缓存时，需建立特定对象的缓存管理器，在对数据进行操作时，需要根据对象来匹配对应的缓存管理器，否则会报错
 * 2、同时有了多个缓存管理器时，需要给系统设置默认的缓存管理器，否则也会报错;@Primary指定的类为默认缓存管理器
 * 3、针对不同的地方使用不同的缓存管理器时，可通过注解来配置管理器：@CacheConfig(cacheNames = "user", cacheManager = "userRedisTemplate")
 */
@Configuration
public class RedisConfig {

    /**
     * 设置Redis的序列化器，使用json方式
     * 针对redisObjTest测试类中使用opsForValue（）的方法进行操作
     * 存储操作对象为User
     *
     * @param redisConnectionFactory
     * @return
     * @throws UnknownHostException
     */
    @Primary
    @Bean
    public RedisTemplate<Object, User> userRedisTemplate(RedisConnectionFactory redisConnectionFactory) throws UnknownHostException {
        RedisTemplate<Object, User> template = new RedisTemplate<Object, User>();
        template.setConnectionFactory(redisConnectionFactory);
        // 新建一个Josn规则的序列化器，添加到配置中;此次操作什么对象，就改为什么对象，类User
        Jackson2JsonRedisSerializer<User> ser = new Jackson2JsonRedisSerializer<User>(User.class);
        template.setDefaultSerializer(ser);

        return template;
    }

    /**
     * 实践中的写法
     * SpringBoot2.3.x使用方法
     * 针对使用了Cacheable注解的方法使用，自定义了CacheManager，使用json序列化的方式
     *
     * @param redisTemplate
     * @return
     */
    @Bean
    public CacheManager kqUserCacheManager(RedisTemplate redisTemplate) {
        RedisCacheWriter redisCacheWriter = RedisCacheWriter.nonLockingRedisCacheWriter(Objects.requireNonNull(redisTemplate.getConnectionFactory()));
        // 创建Jackson对象并传入需要序列化的对象
        Jackson2JsonRedisSerializer<KqUser> serializer = new Jackson2JsonRedisSerializer<KqUser>(KqUser.class);

        RedisSerializationContext<KqUser, KqUser> serializationContext = RedisSerializationContext.fromSerializer(serializer);
        RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig().serializeValuesWith(serializationContext.getValueSerializationPair());

        return new RedisCacheManager(redisCacheWriter, redisCacheConfiguration);
    }

//    /**
//     * SpringBoot2.x以前
//     * @param userRedisTemplate
//     * @return
//     */
//    @Bean
//    public RedisCacheManager UserCacheManager(RedisTemplate<Object, User> userRedisTemplate) {
//        //RedisCacheManager cacheManager = new RedisCacheManager(userRedisTemplate);
//        RedisCacheManager cacheManager = new RedisCacheManager(RedisCacheWriter redisCacheWriter,RedisCacheConfiguration redisCacheConfiguration);
//        cacheManager.setTransactionAware(true);
//        return cacheManager;
//    }

}
