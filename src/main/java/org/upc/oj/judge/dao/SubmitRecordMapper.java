package org.upc.oj.judge.dao;

import org.apache.ibatis.annotations.Mapper;
import org.upc.oj.judge.po.SubmitRecord;

import java.util.List;

@Mapper
public interface SubmitRecordMapper {
    void insert(SubmitRecord record);
    List<SubmitRecord> getSubmitRecordByUsername(String username,Integer qid);
}
