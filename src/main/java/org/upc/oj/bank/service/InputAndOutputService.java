package org.upc.oj.bank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.upc.oj.bank.dao.InputAndOutputMapper;
import org.upc.oj.bank.po.InputAndOutput;

import java.util.List;

@Service
public class InputAndOutputService {
    @Autowired
    private InputAndOutputMapper inputAndOutputMapper;

    /**
     * 查询题目输入输出
     * @param qid 题目id
     * @return
     */
    public List<InputAndOutput> getInputAndOutput(int qid) throws RuntimeException{
        return inputAndOutputMapper.getInputAndOutput(qid);
    }

    /**
     * 删除题目输入输出
     * @param ids 输入输出id集合
     * @return
     */
    public int delInputAndOutPut(int qid,List<Integer> ids){
        return inputAndOutputMapper.delInputAndOutPut(qid,ids);
    }

    /**
     * 添加题目输入输出
     * @param qid 题目id
     * @param inAndOuts 输入输出集合
     * @return
     */
    public int addInputAndOutPut(int qid,List<InputAndOutput> inAndOuts) throws RuntimeException{
        return inputAndOutputMapper.addInputAndOutPut(qid,inAndOuts);
    }

    /**
     * 更改题目输入输出
     * @param inAndOuts 输入输出集合
     * @return
     */
    public void updateInputAndOutput(List<InputAndOutput> inAndOuts) throws RuntimeException{
        inputAndOutputMapper.updateInputAndOutput(inAndOuts);
    }
}
