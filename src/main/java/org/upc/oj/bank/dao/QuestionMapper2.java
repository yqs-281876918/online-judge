package org.upc.oj.bank.dao;

import org.apache.ibatis.annotations.Mapper;
import org.upc.oj.bank.po.Question;

import java.util.List;

@Mapper
public interface QuestionMapper2 {
    int getQuestionCountByDiff(int diff);

    int getFinishedCountByDiff(String username,int diff);

    List<Question> search(String keyword);
}
