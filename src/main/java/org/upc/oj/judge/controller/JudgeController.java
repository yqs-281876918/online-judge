package org.upc.oj.judger.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.upc.oj.judger.service.JudgeService;

import java.util.HashMap;
import java.util.Map;

@RestController("judge")
public class JudgeController {
    @Autowired
    private JudgeService judgeService;
    @PostMapping("code")
    public Map<String,Object> judge(Integer qid, String code){
        String username="yqs";
        return judgeService.judge(qid,code,username);
    }

}
