package org.upc.oj.auth.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import org.upc.oj.auth.po.OnlineJudgeToken;
import org.upc.oj.auth.util.AuthUtil;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Slf4j
public class GateWayInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler){
        String uri=request.getRequestURI();
        if(uri.equals("/auth/login")||uri.equals("/auth/reg")){
            return true;
        }
        Cookie[] cookies=request.getCookies();
        if(cookies==null){
            return failedResponse(response);
        }
        String token=null;
        for(Cookie cookie : cookies){
            if (cookie.getName().equals("token")){
                token=cookie.getValue();
                break;
            }
        }
        if(token==null){
            return failedResponse(response);
        }
        OnlineJudgeToken ojt = AuthUtil.parseToken(token);
        if(ojt==null){
            return failedResponse(response);
        }

        return true;
    }
    public boolean failedResponse(HttpServletResponse response){
        response.setCharacterEncoding("UTF8");
        response.setContentType("JSON");
        try {
            PrintWriter writer = response.getWriter();
            writer.print("{\"status\":\"fail\",\"detail\":\"未登录或者用户权限不足\"}");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
