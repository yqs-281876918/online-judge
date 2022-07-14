package org.upc.oj.auth.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
public class GateWayInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler){
//        String uri=request.getRequestURI();
//        if(uri.equals("/auth/login")){
//            return true;
//        }
//        Cookie[] cookies=request.getCookies();
//        String token=null;
//        for(Cookie cookie : cookies){
//            if (cookie.getName().equals("token")){
//                token=cookie.getValue();
//                break;
//            }
//        }
//        if(token==null){
//            return false;
//        }
        return true;
    }
}
