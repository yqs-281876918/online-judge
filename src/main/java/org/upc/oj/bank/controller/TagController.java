package org.upc.oj.bank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.upc.oj.auth.interceptor.wrapper.AuthedHttpServletRequest;
import org.upc.oj.bank.po.Question;
import org.upc.oj.bank.po.Tag;
import org.upc.oj.bank.service.TagService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/bank")
public class TagController {
    @Autowired
    private TagService tagService;

    //添加标签
    @PostMapping("/tag")
    public Map<String,String> addTag(String title, AuthedHttpServletRequest request){
        Map<String,String> msg=new HashMap<>();
        if (request.getIdentity().equals("admin")){
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
        }else{
            msg.put("status","refused");
            msg.put("msg","权限不足，仅管理员才能对标签进行操作");
            return msg;
        }
    }

    //获取所有标签
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

    //删除标签---------------------------------------------------------------
    @DeleteMapping("/tags")
    public Map<String,Object> delTagByTitle(@RequestParam("ids")List<Integer> ids,AuthedHttpServletRequest request){
        Map<String,Object> map=new HashMap<>();
        if(request.getIdentity().equals("admin")){
            try {
                int delCount=tagService.delTagByTitle(ids);
                map.put("delCount",delCount);
            }catch (RuntimeException e){
                map.put("status","error");
                map.put("delCount",0);
                map.put("msg",e.getCause().getMessage());
                return map;
            }
            map.put("status","success");
            map.put("msg","删除成功");
            return map;
       }else{
            map.put("status","refused");
            map.put("msg","权限不足，仅管理员才能对标签进行操作");
            return map;
        }

    }

    //修改标签-------------------------------------
    @PatchMapping("/tag")
    public Map<String,Object> updateTagById(int id,@RequestParam("title") String newTitle,AuthedHttpServletRequest request){
        Map<String,Object> map=new HashMap<>();
        if(request.getIdentity().equals("admin")){
            try {
                tagService.updateTagById(id,newTitle);
            }catch (RuntimeException e){
                map.put("status","error");
                map.put("msg",e.getCause().getMessage());
                return map;
            }
            map.put("status","success");
            map.put("msg","修改成功");
            return map;
        }else{
            map.put("status","refused");
            map.put("msg","权限不足，仅管理员才能对标签进行操作");
            return map;
        }

    }

    //查询标签
    @GetMapping("/tags/title")
    public Map<String,Object> getTagListByTitle(String title){
        Map<String,Object> map=new HashMap<>();
        try{
            List<Tag> tagList=tagService.getTagListByTitle(title);
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

    //获取标签下问题列表
    @GetMapping("/tag/questions")
    public Map<String,Object> getQuestionListByTag(int id) {
        Map<String,Object> map=new HashMap<>();
        try{
            List<Question> questionList=tagService.getQuestionListByTag(id);
            map.put("status","success");
            map.put("questionList",questionList);
            map.put("count",questionList.size());
            map.put("msg","查询成功");
        }catch (RuntimeException e){
            map.put("status","error");
            map.put("questionList",null);
            map.put("count",0);
            map.put("msg",e.getCause().getMessage());
        }
        return map;
    }
}
