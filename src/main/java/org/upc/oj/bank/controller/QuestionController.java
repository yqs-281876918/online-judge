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

    //获取问题详细信息
    @GetMapping("/question/content")
    public Map<String,Object> getQuestionList(int id) {
        Map<String,Object> map=new HashMap<>();
        try{
            Question q=questionService.getQuestionInf(id);
            map.put("status","success");
            map.put("questionContent",q);
            map.put("msg","查询成功");
        }catch (RuntimeException e){
            map.put("status","error");
            map.put("questionContent",null);
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
    @DeleteMapping("/question")
    public Map<String,Object> delQuestionById(int id){
        Map<String,Object> map=new HashMap<>();
        try {
            int delCount=questionService.delQuestionById(id);
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
    }

    //删除问题多个标签
    @DeleteMapping("/question/tags")
    public Map<String,Object> delQuestionTags(int qid, @RequestParam("tids")List<Integer> tids){
        Map<String,Object> map=new HashMap<>();
        try {
            int delCount=questionService.delQuestionTags(qid,tids);
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
    }

    //向问题插入多个标签
    @PostMapping("/question/tags")
    public Map<String,Object> addQuestionTags(int qid, @RequestParam("tids")List<Integer> tids){
        Map<String,Object> map=new HashMap<>();
        try {
            int addCount=questionService.addQuestionTags(qid,tids);
            map.put("addCount",addCount);
        }catch (RuntimeException e){
            map.put("status","error");
            map.put("addCount",0);
            map.put("msg",e.getCause().getMessage());
            return map;
        }
        map.put("status","success");
        map.put("msg","插入成功");
        return map;
    }

    //更改问题标签
    @PutMapping("/question/tags")
    public Map<String,Object> updateQuestionTags(int qid,@RequestParam("tids")List<Integer> newTids){
        Map<String,Object> map=new HashMap<>();
        try {
            questionService.delQuestionTags(qid,null);
            questionService.addQuestionTags(qid,newTids);
        }catch (RuntimeException e){
            map.put("status","error");
            map.put("msg",e.getCause().getMessage());
            return map;
        }
        map.put("status","success");
        map.put("msg","更改成功");
        return map;
    }

    //添加题目

    //修该题目
}
