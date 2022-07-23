package org.upc.oj.judge.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.upc.oj.judge.dao.SubmitRecordMapper;
import org.upc.oj.judge.po.SubmitRecord;

import java.util.List;

@Service
public class SubmitRecordService {
    @Autowired
    private SubmitRecordMapper submitRecordMapper;
    public void insert(SubmitRecord record){
        submitRecordMapper.insert(record);
    }
    public List<SubmitRecord> getSubmitRecordByUsername(String username,Integer qid){
        return submitRecordMapper.getSubmitRecordByUsername(username,qid);
    }
}
