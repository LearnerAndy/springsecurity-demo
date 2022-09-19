package com.xxxx.security.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

    /**
     * 成功后跳转页面
     */
    @RequestMapping("toMain")
    public String toMain(){
        return "redirect:main.html";
    }
    /**
     * 测试
     */
    @Secured("ROLE_AA")
    @RequestMapping("test")
    public String test(){
        return "Hello SpringSecurity";
    }

    /**
     * 失败后跳转页面
     */
    @RequestMapping("toError")
    public String toError(){
        return "redirect:error.html";
    }
}
