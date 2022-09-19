package com.xxxx.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class UserServiceImpl implements UserDetailsService {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //1.查询数据库判断用户名是否存在， 如果不存在抛出UsernameNotFoundException异常
        if (StringUtils.isEmpty(username) || !(username.equals("admin"))) {
            System.out.println("用户不存在!");
            throw new UsernameNotFoundException("用户记录不存在!");
        }

        //2.到数据库查询用户记录,把查询出来的密码（注册时已经加密过）进行解析，或直接把密码放入构造方法中
        String password = passwordEncoder.encode("123");
        return new User(username, password, AuthorityUtils.
                commaSeparatedStringToAuthorityList("/main1.html，ROLE_CC"));

    }
}
