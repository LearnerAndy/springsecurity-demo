package com.xxxx.security.config;

import com.xxxx.security.handler.MyAccessDeniedHandler;
import com.xxxx.security.handler.MyForwardAuthenticationFailureHandler;
import com.xxxx.security.handler.SpringSecurityAuthenticationSuccessHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.Resource;

@Configuration
public class SecurityConfig05 extends WebSecurityConfigurerAdapter {
    @Resource
    private SpringSecurityAuthenticationSuccessHandler authenticationSuccessHandler;
    @Resource
    private MyAccessDeniedHandler myAccessDeniedHandler;


    protected void configure(HttpSecurity http) throws Exception {
        // 关闭csrf 配置
        http.csrf().disable();

        // 配置表单处理信息  登录页&后端登录请求地址&登录成功页面
        http.formLogin()
                .loginPage("/login.html")
                .loginProcessingUrl("/login")

                .successHandler(authenticationSuccessHandler)
                .failureHandler(new MyForwardAuthenticationFailureHandler("/error.html"));


        // 配置SpringSecurity 权限
        http.authorizeRequests()
                .antMatchers("/login.html").permitAll()

                .anyRequest().authenticated();
        //异常处理
        http.exceptionHandling().accessDeniedHandler(myAccessDeniedHandler);
    }

    @Bean
    public PasswordEncoder getPw() {
        return new BCryptPasswordEncoder();
    }
}
