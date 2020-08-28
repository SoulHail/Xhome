package com.codebear.xhome.config;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Security配置，类似拦截器配置
 */
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * 定义授权规则
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //super.configure(http);

        // 重写此方法，定制请求的授权规则
        // 带"/"的允许所有人访问,访问level1路径下的，只针对VIP1能访问。依次VIP2.。。
        http.authorizeRequests().antMatchers("/").permitAll()
                                .antMatchers("/level1/**").hasRole("VIP1")
                                .antMatchers("/level2/**").hasRole("VIP2")
                                .antMatchers("/level3/**").hasRole("VIP3");

        // 开启自动配置的登陆功能 效果：如果没有登录，没有权限就会来到登录页面
        http.formLogin().loginPage("/index");
        // 1、/login来到登录页
        // 2、重定向到/login？error表示登录失败
        // 3、更多详细规定。。。

        // 开启自动配置的注销功能
        http.logout().logoutSuccessUrl("/index"); // 注销成功后来到首页
        // 1、访问/logout表示用户注销，清空session
        // 2、注销成功会返回/login？logout页面

        // 开启记住我功能，登录一次后，再次访问网址会自动登录
        http.rememberMe();
        // 点击注销会删除cookie
    }

    /**
     * 定义认证规则
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //super.configure(auth);

        // 重写此方法，定制新的认证规则
        // 身份可定义多个,每一次withUser（）新加一个身份
        auth.inMemoryAuthentication()
                .withUser("zhangsan").password("123").roles("VIP1","VIP3")
                .and()
                .withUser("lisi").password("123").roles("VIP2")
                .and()
                .withUser("wangwu").password("123").roles("VIP1","VIP2","VIP3");
    }

}
