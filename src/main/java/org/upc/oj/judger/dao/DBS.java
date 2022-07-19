package org.upc.oj.judger.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import org.upc.oj.judger.po.TestIOFile;

import java.util.List;
@Mapper
@Repository
public interface DBS {
    public List<TestIOFile> GetTestByQid(String Qid);
}
