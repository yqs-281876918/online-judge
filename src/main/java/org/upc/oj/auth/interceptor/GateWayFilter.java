package org.upc.oj.auth.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.upc.oj.auth.config.RouteConfig;
import org.upc.oj.auth.interceptor.wrapper.AuthedHttpServletRequest;
import org.upc.oj.auth.po.OnlineJudgeToken;
import org.upc.oj.auth.util.AuthUtil;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Component
@WebFilter(filterName = "gateway")
public class GateWayFilter implements Filter {
    @Autowired
    private RouteConfig routeConfig;
    public boolean setResponseFailed(HttpServletResponse response){
        response.setStatus(403);
        response.setCharacterEncoding("UTF8");
        response.setContentType("JSON");
        try {
            PrintWriter writer = response.getWriter();
            writer.print("登录过期或未登录");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest=(HttpServletRequest)servletRequest;
        HttpServletResponse httpResponse=(HttpServletResponse)servletResponse;
        String uri=httpRequest.getRequestURI();
        if(uri.startsWith("/auth/login")||uri.startsWith("/auth/reg")||uri.startsWith("/auth/admin/login")){
            filterChain.doFilter(servletRequest,servletResponse);
            return;
        }
        OnlineJudgeToken ojt = getOnlineJudgeToken(servletRequest);
        if(routeConfig.getReleaseUrls().contains(uri)){
            AuthedHttpServletRequest authedHttpServletRequest= new AuthedHttpServletRequest(httpRequest);
            if(ojt!=null&&!ojt.isExpired()){
                authedHttpServletRequest.setUsername(ojt.getUserName());
                authedHttpServletRequest.setIdentity(ojt.getIdentity());
            }
            filterChain.doFilter(authedHttpServletRequest,servletResponse);
        }else {
            if(ojt==null||ojt.isExpired()){
                setResponseFailed(httpResponse);
                return;
            }
            AuthedHttpServletRequest authedHttpServletRequest=new AuthedHttpServletRequest(httpRequest);
            authedHttpServletRequest.setUsername(ojt.getUserName());
            authedHttpServletRequest.setIdentity(ojt.getIdentity());
            filterChain.doFilter(authedHttpServletRequest,servletResponse);
        }
    }
    private OnlineJudgeToken getOnlineJudgeToken(ServletRequest servletRequest){
        HttpServletRequest httpRequest=(HttpServletRequest)servletRequest;
        Cookie[] cookies=httpRequest.getCookies();
        if(cookies==null){
            return null;
        }
        String token=null;
        for(Cookie cookie : cookies){
            if (cookie.getName().equals("token")){
                token=cookie.getValue();
                break;
            }
        }
        if(token==null){
            return null;
        }
        return AuthUtil.parseToken(token);
    }
}
