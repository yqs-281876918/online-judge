package org.upc.oj.judge.dao;

import org.apache.ibatis.annotations.Mapper;
import org.upc.oj.bank.po.Question;
import org.upc.oj.judge.po.QuestionIO;

import java.util.List;

@Mapper
public interface JudgeMapper {
    List<QuestionIO> queryQuestionIO(Integer qid);
    Question queryQuestion(Integer id);
}
