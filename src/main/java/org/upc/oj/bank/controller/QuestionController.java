package org.upc.oj.bank.controller;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.upc.oj.bank.po.Question;
import org.upc.oj.bank.po.Tag;
import org.upc.oj.bank.service.QuestionService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/bank")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    //获取问题列表
    @GetMapping("/questions")
    public Map<String,Object> getQuestionList(Question q) {
        Map<String,Object> map=new HashMap<>();
        try{
            List<Question> questionList=questionService.getQuestionList(q);
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

    //获取问题所有标签
    @GetMapping("/question/tags")
    public Map<String,Object> getTagList(int id) {
        Map<String,Object> map=new HashMap<>();
        try{
            List<Tag> tagList=questionService.getQuestionTag(id);
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

    //删除问题
    @PatchMapping("/question")
    public Map<String,Object> delQuestionById(int id){
        Map<String,Object> map=new HashMap<>();
        try {
            questionService.delQuestionById(id);
        }catch (RuntimeException e){
            map.put("status","error");
            map.put("msg",e.getCause().getMessage());
            return map;
        }
        map.put("status","success");
        map.put("msg","删除成功");
        return map;
    }

    //删除问题多个标签
    @DeleteMapping("/question/tags")
    public Map<String,Object> delQuestionTags(int qid, @RequestParam("tids")List<Integer> tids){
        Map<String,Object> map=new HashMap<>();
        try {
            questionService.delQuestionTags(qid,tids);
        }catch (RuntimeException e){
            map.put("status","error");
            map.put("delCount",0);
            map.put("msg",e.getCause().getMessage());
            return map;
        }
        map.put("status","success");
        map.put("delCount",tids.size());
        map.put("msg","删除成功");
        return map;
    }

    //向问题插入多个标签
    @PostMapping("/question/tags")
    public Map<String,Object> addQuestionTags(int qid, @RequestParam("tids")List<Integer> tids){
        Map<String,Object> map=new HashMap<>();
        try {
            questionService.addQuestionTags(qid,tids);
        }catch (RuntimeException e){
            map.put("status","error");
            map.put("addCount",0);
            map.put("msg",e.getCause().getMessage());
            return map;
        }
        map.put("status","success");
        map.put("addCount",tids.size());
        map.put("msg","插入成功");
        return map;
    }

}
