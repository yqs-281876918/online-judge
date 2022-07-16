package org.upc.oj.bank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.upc.oj.bank.po.Tag;
import org.upc.oj.bank.service.TagService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/bank")
public class TagController {
    @Autowired
    private TagService tagService;
    @PostMapping("/tag")
    public Map<String,String> addTag(String title){
        Map<String,String> msg=new HashMap<>();
        try {
            tagService.createTag(title);
        }catch (RuntimeException e){
            msg.put("status","error");
            msg.put("msg",e.getCause().getMessage());
            return msg;
        }
        msg.put("status","success");
        msg.put("msg","插入成功");
        return msg;
    }

    @GetMapping("/tags")
    public Map<String,Object> getTagList() {
        Map<String,Object> map=new HashMap<>();
        try{
            List<Tag> tagList=tagService.getTagList();
            map.put("status","success");
            map.put("tagList",tagList);
            map.put("count",tagList.size());
            map.put("msg","查询成功");
        }catch (RuntimeException e){
            map.put("status","error");
            map.put("tagList",null);
            map.put("count",0);
            map.put("msg",e.getCause().getMessage());
        }
        return map;
    }
    @DeleteMapping("/tag")
    public Map<String,Object> delTagByTitle(String title){
        Map<String,Object> map=new HashMap<>();
        try {
            tagService.delTagByTitle(title);
        }catch (RuntimeException e){
            map.put("status","error");
            map.put("msg",e.getCause().getMessage());
            return map;
        }
        map.put("status","success");
        map.put("msg","删除成功");
        return map;
    }
}
