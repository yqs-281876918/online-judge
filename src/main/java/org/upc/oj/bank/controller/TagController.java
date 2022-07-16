package org.upc.oj.bank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
    @PostMapping("/addTag")
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

    @RequestMapping("/getTag")
    public HashMap getTagList() {
        HashMap map=new HashMap<>();
        try{
            List<Tag> tagList=tagService.getTagList();
            map.put("status","success");
            map.put("tagList",tagList);
            map.put("count",tagList.size());
            map.put("msg","查询成功");

        }catch (Exception e){
            map.put("status","error");
            map.put("tagList",null);
            map.put("count",0);
            map.put("msg",e.getCause().getMessage());
        }
        return map;
    }
    @PostMapping("/delTag")
    public HashMap delTagByTitle(String title){
        HashMap map=new HashMap<>();
        String errMsg=tagService.delTagByTitle(title);
        if(errMsg==null){
            map.put("status","success");
            map.put("msg","删除成功");
        }else {
            map.put("status","error");
            map.put("msg",errMsg);
        }
        return map;
    }
}
