package org.upc.oj.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.upc.oj.auth.config.RouteConfig;
import org.upc.oj.interceptor.wrapper.AuthedHttpServletRequest;
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
        Cookie[] cookies=httpRequest.getCookies();
        String token=null;
        if(cookies!=null){
            for(Cookie cookie : cookies){
                if (cookie.getName().equals("token")){
                    token=cookie.getValue();
                    break;
                }
            }
        }
        OnlineJudgeToken ojt = AuthUtil.parseToken(token);
        String uri=httpRequest.getRequestURI();
        uri=uri.split("\\?")[0];
        if(uri.equals("/favicon.ico")||uri.startsWith("/fonts/")||
                uri.startsWith("/js/")||uri.startsWith("/css/")||
                uri.startsWith("/img/")||routeConfig.getRouters().contains(uri)){
            AuthedHttpServletRequest authedHttpServletRequest=new AuthedHttpServletRequest(httpRequest);
            if(ojt!=null){
                authedHttpServletRequest.setUsername(ojt.getUserName());
                authedHttpServletRequest.setIdentity(ojt.getIdentity());
            }
            filterChain.doFilter(authedHttpServletRequest,servletResponse);
            return;
        }
        if(ojt==null||ojt.isExpired()){
            setResponseFailed(httpResponse);
            return;
        }
        AuthedHttpServletRequest authedHttpServletRequest=new AuthedHttpServletRequest(httpRequest);
        authedHttpServletRequest.setUsername(ojt.getUserName());
        authedHttpServletRequest.setIdentity(ojt.getIdentity());
        filterChain.doFilter(authedHttpServletRequest,servletResponse);
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
