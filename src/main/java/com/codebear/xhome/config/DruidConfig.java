package com.codebear.xhome.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Druid配置类
 * 整合Druid数据源的配置信息
 * 下面平台配置完成后，启动项目访问http://localhost:8181/druid，可访问监控到的数据信息
 */
@Configuration
public class DruidConfig {

    /**
     * 将druid数据源的其他配置生效
     * @return
     */
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource druid() {
        return new DruidDataSource();
    }

    // 配置Druid的监控
    // 1、配置一个管理后台的Servlet
    @Bean
    public ServletRegistrationBean statViewServlet() {
        ServletRegistrationBean bean = new ServletRegistrationBean(new StatViewServlet(),"/druid/*");
        // 配置一些初始化参数
        Map<String,String> initParams = new HashMap<>();
        initParams.put("loginUsername","admin"); // 登录初始用户名
        initParams.put("loginPassword","123456"); // 登录初始密码
        initParams.put("allow",""); // 允许谁访问，为空的话就是所有
        //initParams.put("deny","localhost"); // 拒绝谁访问
        bean.setInitParameters(initParams);
        return bean;
    }

    // 2、配置一个web监控filter
    @Bean
    public FilterRegistrationBean webStatFilter() {
        FilterRegistrationBean bean = new FilterRegistrationBean();
        bean.setFilter(new WebStatFilter());
        // 配置一些初始化参数,配置一些可以不用拦截的属性
        Map<String,String> initParams = new HashMap<>();
        initParams.put("exclusions","*.js,*.css,/druid/*"); // 一些静态资源
        bean.setInitParameters(initParams);
        // 拦截哪些请求
        bean.setUrlPatterns(Arrays.asList("/*"));
        return bean;
    }
}
