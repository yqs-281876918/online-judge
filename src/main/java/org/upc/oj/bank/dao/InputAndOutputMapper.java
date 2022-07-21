package org.upc.oj.bank.dao;

import org.apache.ibatis.annotations.Mapper;
import org.upc.oj.bank.po.InputAndOutput;

import java.util.List;

@Mapper
public interface InputAndOutputMapper {
    /**
     * 查询题目的输入输出信息
     * @param qid 题目id
     * @return 输入输出集合
     */
    public List<InputAndOutput> getInputAndOutput(int qid);
    ;
    /**
     * 删除题目输入输出
     * @param qid,ids 题目id, 输入输出id集合
     * @return
     */
    public int delInputAndOutPut(int qid,List<Integer> ids);

    /**
     * 添加题目输入输出
     * @param qid,inAndOuts 题目id, 输入输出集合
     * @return
     */
    public int addInputAndOutPut(int qid,List<InputAndOutput> inAndOuts);

    /**
     * 更改题目输入输出
     * @param inAndOuts
     * @return
     */
    public void updateInputAndOutput(List<InputAndOutput> inAndOuts);
}
