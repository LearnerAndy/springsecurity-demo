package com.xxxx.security;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class MyTest {

    @Test
    public void test() {
        //创建解析器
        BCryptPasswordEncoder pw = new BCryptPasswordEncoder();
        //对密码加密
        String encode = pw.encode("123");
        System.out.println(encode);
        //判断原字符和加密后内容是否匹配
        boolean matches = pw.matches("123", encode);
        System.out.println("==================="+matches);
        boolean matches02 = pw.matches("123", encode);
        System.out.println("~~~~~~~~~~~~~~~~~~~"+matches02);

    }
}
