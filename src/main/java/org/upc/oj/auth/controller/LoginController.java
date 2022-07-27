package org.upc.oj.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.upc.oj.auth.dto.LoginRequestParam;
import org.upc.oj.auth.po.OJUser;
import org.upc.oj.auth.service.LoginService;

import javax.servlet.http.Cookie;
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
     * @param param    请求body
     * @param response 略
     * @return 返回认证结果json
     */
    @PostMapping(path = "/login")
    public Map<String, String> getToken(@RequestBody(required = false) LoginRequestParam param, HttpServletResponse response) {
        String username = null;
        String password = null;
        if (param != null) {
            username = param.getUsername();
            password = param.getPassword();
        }
        if (username == null) {
            username = "";
        }
        if (password == null) {
            password = "";
        }
        String token = loginService.getTokenByLogin(username, password);
        Map<String, String> msg = new HashMap<>();
        if (token == null) {
            msg.put("status", "error");
            msg.put("msg", "认证失败,账号不存在或者密码错误");
            return msg;
        }
        msg.put("status", "success");
        msg.put("username", username);
        Cookie token_cookie = new Cookie("token", token);
        token_cookie.setMaxAge(Integer.MAX_VALUE);
        token_cookie.setPath("/");
        response.addCookie(token_cookie);

        return msg;
    }

    @PostMapping(path = "/logout")
    public Map<String, String> logout(HttpServletResponse response) {
        Cookie cookie = new Cookie("token", "");
        cookie.setMaxAge(0);
        cookie.setPath("/");
        response.addCookie(cookie);
        Map<String, String> msg = new HashMap<>();
        msg.put("status", "success");
        return msg;
    }

    @PostMapping("/admin/login")
    public Map<String,Object> adminLogin(@RequestBody(required = false) LoginRequestParam param){
        Map<String,Object> msg = new HashMap<>();
        String username = param.getUsername();
        String password = param.getPassword();
        OJUser user = loginService.getUser(username, password);
        if(user==null){
            msg.put("status","fail");
        }else {
            if(user.getIdentity().equals("admin")&&user.getIdentity().equals("super"));
        }
        return msg;
    }
}
