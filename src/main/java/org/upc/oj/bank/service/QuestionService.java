package org.upc.oj.bank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.upc.oj.bank.dao.QuestionMapper;
import org.upc.oj.bank.dto.QuestionList;
import org.upc.oj.bank.dto.QuestionWrapper;
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
     * @param start 问题起始位置
     * @param pageSize 每页问题数量
     * @param tid 标签id
     * @return 问题集
     */
    public List<QuestionList> getQuestionList(QuestionList q, int start, int pageSize,String username,String tid,int flg) throws RuntimeException{
        return questionMapper.getQuestionList(q,start,pageSize,username,tid,flg);
    }

    /**
     * 获取问题详细信息
     * @param id 题目id
     * @return 问题对象
     */
    public QuestionWrapper getQuestionInf(int id,String username){
        return questionMapper.getQuestionInf(id,username);
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
    public int delQuestionById(int id) throws RuntimeException{
        return questionMapper.delQuestionById(id);
    }
    /**
     * 删除题目标签
     * @param qid 问题qid
     * @param tids 标签tids
     * @return
     */
    public int delQuestionTags(int qid,List<Integer> tids) throws RuntimeException{
        return questionMapper.delQuestionTags(qid,tids);
    }

    /**
     * 添加题目标签
     * @param qid 问题qid
     * @param tids 标签tids，若为空，表示删除该问题所有标签
     * @return
     */
    public int addQuestionTags(int qid,List<Integer> tids) throws RuntimeException{
        return questionMapper.addQuestionTags(qid,tids);
    }

    /**
     * 添加题目
     * @param q 问题对象
     * @return
     */
    public int addQuestion(Question q)throws RuntimeException{
        return questionMapper.addQuestion(q);
    }

    /**
     * 修改题目
     * @param q 问题对象
     * @return
     */
    public int updateQuestion(Question q){
        return questionMapper.updateQuestion(q);
    }

    /**
     * 获取题目总数量
     * @param q 问题对象
     * @param username 用户名
     * @param tid 标签id
     * @return
     */
    public int getQuestionCount(QuestionList q, String username,String tid,int flg){
        return questionMapper.getQuestionCount(q,username,tid,flg);
    }
}
