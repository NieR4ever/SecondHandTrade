package com.example.sht.interceptor;

import com.example.sht.domain.Data;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登录的权限拦截器
 */
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (request.getSession().getAttribute("loginUser")!=null){
            return true;
        } else {
            Data data = new Data();
            data.setErrorCode(3);
            data.setMsg("尚未登录");
            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(data);
            response.getWriter().println(json);
            return false;
        }
    }
}
