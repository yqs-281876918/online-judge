package org.upc.oj.auth.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.upc.oj.interceptor.wrapper.AuthedHttpServletRequest;

import java.util.HashMap;
import java.util.Map;

@RestController()
@RequestMapping("/auth")
public class PingController {
    @GetMapping("ping")
    public Map<String,String> ping(AuthedHttpServletRequest request){
        Map<String,String> msg = new HashMap<>();
        msg.put("username",request.getUsername());
        return msg;
    }
}
