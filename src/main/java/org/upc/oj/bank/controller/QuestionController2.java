package org.upc.oj.bank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.upc.oj.auth.interceptor.wrapper.AuthedHttpServletRequest;
import org.upc.oj.bank.service.QuestionService2;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/bank")
public class QuestionController2 {
    @Autowired
    private QuestionService2 questionService2;
    @GetMapping("question/finish")
    public Map<Object,Object> getQuestionCount(AuthedHttpServletRequest request){
        Map<Object,Object> msg = new HashMap<>();
        int[] counts=new int[4];
        for(int diff=1;diff<=3;diff++){
            counts[diff]=questionService2.getQuestionCountByDiff(diff);
        }
        int[] finishCounts=new int[4];
        for(int diff=1;diff<=3;diff++){
            finishCounts[diff]=questionService2.getFinishedCountByDiff(request.getUsername(),diff);
        }
        msg.put("total",counts);
        msg.put("finish",finishCounts);
        return msg;
    }
}
