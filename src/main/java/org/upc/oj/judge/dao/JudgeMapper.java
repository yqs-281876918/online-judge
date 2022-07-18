package org.upc.oj.judger.dao;

import org.apache.ibatis.annotations.Mapper;
import org.upc.oj.judger.po.IOData;
import org.upc.oj.judger.po.Question;

import java.util.List;

@Mapper
public interface JudgeMapper {
    List<IOData> queryIOData(Integer qid);
    Question queryQuestion(Integer id);
}
