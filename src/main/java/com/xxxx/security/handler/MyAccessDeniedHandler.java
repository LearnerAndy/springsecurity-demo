package com.xxxx.security.handler;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Component
public class MyAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        //设置此响应的状态代码
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);//403
        //设置具有给定名称和值的响应标头。如果已经设置了标头，则新值将覆盖前一个值。
        response.setHeader("Content-Type","application/json;charset=utf-8");
        //转json
        PrintWriter out = response.getWriter();
        out.write("{\"status\":\"error\",\"msg\":\"权限不足，请联系管理员！\"}");
        out.flush();
        out.close();
    }
}
