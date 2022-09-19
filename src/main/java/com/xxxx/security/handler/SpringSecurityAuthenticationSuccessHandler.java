package com.xxxx.security.handler;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Component
public class SpringSecurityAuthenticationSuccessHandler  implements
        AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {

        response.setContentType("application/json;charset=utf-8");
        response.setCharacterEncoding("utf-8");
        PrintWriter pw= null;
        pw =response.getWriter();
        pw.write("{\"code\":200,\"msg\":\"用户登录成功\"}");
        pw.flush();
        pw.close();
    }
}
