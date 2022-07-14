package org.upc.oj.bank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.upc.oj.bank.service.TagService;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/bank")
public class TagController {
    @Autowired
    private TagService tagService;
    @PostMapping("/tag")
    public Map<String,String> addTag(String title){
        Map<String,String> msg=new HashMap<>();
        String errMsg=tagService.createTag(title);
        if(errMsg==null){
            msg.put("status","success");
            msg.put("msg","插入成功");
        }else {
            msg.put("status","error");
            msg.put("msg",errMsg);
        }
        return msg;
    }
}
