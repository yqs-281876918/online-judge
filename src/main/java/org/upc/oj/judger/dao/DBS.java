package org.upc.oj.judger.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import org.upc.oj.judger.po.DBupdata;
import org.upc.oj.judger.po.JudgeLogMap;
import org.upc.oj.judger.po.TestIOFile;

import java.util.List;
@Mapper
@Repository
public interface DBS {
    public List<TestIOFile> GetTestByQid(String Qid);
    public List<JudgeLogMap> GetJudgeLog(String username,String qid);
    public void InsertJudgeLog(JudgeLogMap map);
    public void updataquestion(DBupdata updata);

}
