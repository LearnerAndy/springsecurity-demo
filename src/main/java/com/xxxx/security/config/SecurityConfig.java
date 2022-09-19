package com.xxxx.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

//@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 关闭csrf 配置
        http.csrf().disable();

        // 配置表单处理信息  登录页&后端登录请求地址&登录成功页面
        http.formLogin()
                //自定义登录页面
                .loginPage("/login.html")
                //当发现/login时认为是登录，必须和表单提交的地址一样。去执行UserServiceImpl
                .loginProcessingUrl("/login")
                // 登录成功页面跳转，POST请求
                .successForwardUrl("/toMain")
                // 登录失败页面跳转和successForwardUrl不能共存
                .failureForwardUrl("/toError");
                // 设置登录表单参数名称要与表单一致
//                .usernameParameter("userName")
//                .passwordParameter("userPwd");


        // 配置SpringSecurity 权限
        http.authorizeRequests()
                // 配置放行资源
                .antMatchers("/login.html","/login","/error.html").permitAll()
                // 其他资源必须登录才能访问
                .anyRequest().authenticated();

    }

    @Bean
    public PasswordEncoder getPw(){
        return new BCryptPasswordEncoder();
    }
}
