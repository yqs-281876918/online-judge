package org.upc.oj.bank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.upc.oj.bank.dto.IORequestData;
import org.upc.oj.bank.po.InputAndOutput;
import org.upc.oj.bank.service.InputAndOutputService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/bank")
public class InputAndOutputController {
    @Autowired
    private InputAndOutputService inputAndOutputService;

    //获取输入输出信息
    @GetMapping("/question/io")
    public Map<String,Object> getInputAndOutput(int qid){
        Map<String,Object> map = new HashMap<>();
        try{
            List<InputAndOutput> inputAndOutput = inputAndOutputService.getInputAndOutput(qid);
            map.put("status","success");
            map.put("qid",qid);
            map.put("inputAndOutput",inputAndOutput);
            map.put("count",inputAndOutput.size());
            map.put("msg","查询成功");
        }catch(RuntimeException e){
            map.put("status","error");
            map.put("qid",qid);
            map.put("inputAndOutput",null);
            map.put("count",0);
            map.put("msg",e.getCause().getMessage());
        }
        return map;
    }

    //删除输入输出
    @DeleteMapping("/question/io")
    public Map<String,Object> delInputAndOutput(int qid, @RequestParam("ids")List<Integer> ids){
        Map<String,Object> map=new HashMap<>();
        try {
            int delCount=inputAndOutputService.delInputAndOutPut(qid,ids);
            map.put("delCount",delCount);
        }catch (Exception e){
            map.put("status","error");
            map.put("qid",qid);
            map.put("delCount",0);
            map.put("msg",e.getCause().getMessage());
            return map;
        }
        map.put("status","success");
        map.put("qid",qid);
        map.put("msg","删除成功");
        return map;
    }

    //添加输入输出-----------------------
    @PostMapping("/question/io")
    public Map<String,Object> addInputAndOutput(@RequestBody IORequestData ioData){
        Map<String,Object> map=new HashMap<>();
        try {
            int addCount=inputAndOutputService.addInputAndOutPut(ioData.getQid(),ioData.getIos());
            map.put("addCount",addCount);
        }catch (RuntimeException e){
            map.put("status","error");
            map.put("qid",ioData.getQid());
            map.put("addCount",0);
            map.put("msg",e.getCause().getMessage());
            return map;
        }
        map.put("status","success");
        map.put("qid",ioData.getQid());
        map.put("msg","插入成功");
        return map;
    }

    //修改输入输出
    @PutMapping("/question/io")
    public Map<String,Object> updateInputAndOutput(@RequestBody IORequestData ioData){
        Map<String,Object> map=new HashMap<>();
        try {
            inputAndOutputService.delInputAndOutPut(ioData.getQid(),null);//删除所有输入输出
            inputAndOutputService.addInputAndOutPut(ioData.getQid(),ioData.getIos());//添加
        }catch (RuntimeException e){
            map.put("status","error");
            map.put("qid",ioData.getQid());
            map.put("addCount",0);
            map.put("msg",e.getCause().getMessage());
            return map;
        }
        map.put("status","success");
        map.put("qid",ioData.getQid());
        map.put("addCount",ioData.getIos().size());
        map.put("msg","修改成功");
        return map;
    }
}
