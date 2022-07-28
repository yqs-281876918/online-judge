package org.upc.oj.bank.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.upc.oj.bank.dto.QuestionList;
import org.upc.oj.bank.dto.QuestionWrapper;
import org.upc.oj.bank.po.Question;
import org.upc.oj.bank.po.Tag;

import java.util.List;

@Mapper
public interface QuestionMapper {

    /**
     * 获取问题列表
     * @param q question对象，若为空，则查询所有问题
     * @param start 问题起始位置
     * @param pageSize 每页问题数量
     * @param username 用户名
     * @param flg 标记是否需要根据用户名筛选
     * @return 问题列表集
     */
    public List<QuestionList> getQuestionList(@Param("q") QuestionList q, @Param("start") int start, @Param("pageSize") int pageSize,
                                              @Param("username")String username,@Param("tid") String tid ,@Param("flg") int flg);

    /**
     * 获取问题详细信息
     * @param id 题目id
     * @param username 用户名
     * @return 问题对象
     */
    public QuestionWrapper getQuestionInf(int id,@Param("username")String username);

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
    public int delQuestionById(int id);

    /**
     * 删除题目标签
     * @param qid 问题qid
     * @param tids 标签tid
     * @return
     */
    public int delQuestionTags(int qid,List<Integer> tids);


    /**
     * 添加题目标签
     * @param qid 问题qid
     * @param tids 标签tids
     * @return
     */
    public int addQuestionTags(int qid,List<Integer> tids);

    /**
     * 添加题目
     * @param q 问题对象
     * @return
     */
    public int addQuestion(Question q);

    /**
     * 修改题目
     * @param q 问题对象
     * @return
     */
    public int updateQuestion(Question q);

    /**
     * 获取题目总数量
     * @param q 问题对象
     * @param flg 标记是否需要根据用户名筛选
     * @return 题目数量
     */
    public int getQuestionCount(QuestionList q, @Param("username")String username,@Param("tid") String tid,@Param("flg") int flg);
}
