package org.upc.oj.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.upc.oj.auth.service.LoginService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@RestController()
@RequestMapping("/auth")
public class LoginController {
    @Autowired
    private LoginService loginService;

    /**
     * 登录认证
     *
     * @param username 用户名
     * @param password 密码
     * @param response 略
     * @return 返回认证结果json
     */
    @PostMapping(path = "/login")
    public Map<String, String> getToken(String username, String password, HttpServletResponse response) {
        if(username==null){
            username="";
        }
        if(password==null){
            password="";
        }
        String token = loginService.getTokenByLogin(username, password);
        Map<String, String> msg = new HashMap<>();
        if (token == null) {
            msg.put("status", "error");
            msg.put("msg", "认证失败,账号不存在或者密码错误");
            return msg;
        }
        msg.put("status", "success");
        msg.put("token", token);
        Cookie token_cookie = new Cookie("token", token);
        token_cookie.setMaxAge((int) (LoginService.lifeTime / 1000));
        response.addCookie(token_cookie);
        return msg;
    }
    @PostMapping(path="/logout")
    public Map<String, String> getToken(HttpServletResponse response) {
        Cookie cookie = new Cookie("token","");
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        Map<String,String> msg=new HashMap<>();
        msg.put("status","success");
        return msg;
    }
}
