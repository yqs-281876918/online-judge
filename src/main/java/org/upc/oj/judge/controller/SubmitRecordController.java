package org.upc.oj.judge.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.upc.oj.interceptor.wrapper.AuthedHttpServletRequest;
import org.upc.oj.judge.po.SubmitRecord;
import org.upc.oj.judge.service.SubmitRecordService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/record")
public class SubmitRecordController {
    @Autowired
    private SubmitRecordService submitRecordService;
    @GetMapping("/all")
    public List<SubmitRecord> getRecords(AuthedHttpServletRequest request,Integer qid){
        if(request.getUsername()==null){
            return new ArrayList<>();
        }
        return submitRecordService.getSubmitRecordByUsername(request.getUsername(),qid);
    }
}
