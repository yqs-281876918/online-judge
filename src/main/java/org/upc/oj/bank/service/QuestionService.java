package org.upc.oj.bank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.upc.oj.bank.dao.QuestionMapper;
import org.upc.oj.bank.po.Question;
import org.upc.oj.bank.po.Tag;

import java.util.List;

@Service
public class QuestionService {

    @Autowired
    private QuestionMapper questionMapper;
    /**
     * 获取问题列表
     * @param q question对象
     * @return 问题集
     */
    public List<Question> getQuestionList(Question q) throws RuntimeException{
        return questionMapper.getQuestionList(q);
    }
    /**
     * 获取问题标签
     * @param id 问题标签
     * @return 标签集
     */
    public List<Tag> getQuestionTag(int id) throws RuntimeException{
        return questionMapper.getQuestionTag(id);
    }
    /**
     * 删除问题
     * @param id 问题id
     * @return
     */
    public void delQuestionById(int id)throws RuntimeException{
        questionMapper.delQuestionById(id);
    }
    /**
     * 删除题目标签
     * @param qid,tid 问题qid,标签tid
     * @return
     */
    public void delQuestionTags(int qid,List<Integer> tids)throws RuntimeException{
        questionMapper.delQuestionTags(qid,tids);
    }

    /**
     * 添加题目标签
     * @param qid,tid 问题qid,标签tid
     * @return
     */
    public void addQuestionTags(int qid,List<Integer> tids)throws RuntimeException{
        questionMapper.addQuestionTags(qid,tids);
    }
}
