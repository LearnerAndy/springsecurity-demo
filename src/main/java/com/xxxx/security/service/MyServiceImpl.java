package com.xxxx.security.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;

@Component
public class MyServiceImpl implements MyService{
    @Override
    public boolean hasPermission(HttpServletRequest request, Authentication authentication) {
        //被认证的Principal或认证后的认证委托人。
        Object obj = authentication.getPrincipal();
        if (obj instanceof UserDetails){
            UserDetails userDetails = (UserDetails) obj;
            //返回授予用户的权限。无法返回null 。
            Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
            //一个String ，包含从协议名称到查询字符串的 URL 部分
            String uri = request.getRequestURI();
            //GrantedAuthority的基本具体实现。存储授予Authentication对象的权限的String表示形式。
            SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(uri);
            //contains()如果此集合包含指定元素，则为true
            return authorities.contains(simpleGrantedAuthority);
        }
        return false;
    }
}
