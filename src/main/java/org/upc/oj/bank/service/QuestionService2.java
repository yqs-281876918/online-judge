package org.upc.oj.bank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.upc.oj.bank.dao.QuestionMapper;
import org.upc.oj.bank.dao.QuestionMapper2;

@Service
public class QuestionService2 {
    @Autowired
    private QuestionMapper2 questionMapper2;
    public int getFinishedCountByDiff(String username,int diff){
        return questionMapper2.getFinishedCountByDiff(username,diff);
    }

    public int getQuestionCountByDiff(int diff){
        if(diff<1||diff>3){
            return 0;
        }
        return questionMapper2.getQuestionCountByDiff(diff);
    }
}
