package org.upc.oj.bank.dao;

import org.apache.ibatis.annotations.Mapper;
import org.upc.oj.bank.po.Question;
import org.upc.oj.bank.po.Tag;

import java.util.List;

@Mapper
public interface QuestionMapper {

    /**
     * 获取问题列表
     * @param q question对象
     * @return 问题集
     */
    public List<Question> getQuestionList(Question q);

    /**
     * 获取问题标签
     * @param id 问题id
     * @return 标签集
     */
    public List<Tag> getQuestionTag(int id);

    /**
     * 删除问题
     * @param id 问题id
     * @return
     */
    public void delQuestionById(int id);

    /**
     * 删除题目标签
     * @param qid,tid 问题qid,标签tid
     * @return
     */
    public void delQuestionTags(int qid,List<Integer> tids);

    /**
     * 添加题目标签
     * @param qid,tid 问题qid,标签tid
     * @return
     */
    public void addQuestionTags(int qid,List<Integer> tids);

}
