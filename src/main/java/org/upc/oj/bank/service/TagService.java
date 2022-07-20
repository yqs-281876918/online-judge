package org.upc.oj.bank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.upc.oj.bank.dao.TagMapper;
import org.upc.oj.bank.po.Question;
import org.upc.oj.bank.po.Tag;

import java.util.List;

@Service
public class TagService {

    @Autowired
    private TagMapper tagMapper;



    /**
     * 增加标签
     * @param title
     * @return
     */
    public int createTag(String title) throws RuntimeException{
        return tagMapper.insertTag(title);
    }
    /**
     * 查询所有标签
     * @param
     * @return
     */
    public List<Tag> getTagList() throws RuntimeException{
        return tagMapper.getTagList();
    }
    /**
     * 删除标签
     * @param ids
     * @return
     */
    public int  delTagByTitle(List<Integer> ids) throws RuntimeException{
        return tagMapper.delTagByTitle(ids);
    }
    /**
     * 修改某标签名称
     * @param id, 标签id
     * @param newTitle 新标签名
     * @return
     */
    public int  updateTagById(int id,String newTitle) throws RuntimeException{
        return tagMapper.updateTagById(id,newTitle);
    }
    /**
     * 查询某标签
     * @param title
     * @return
     */
    public List<Tag> getTagListByTitle(String title) throws RuntimeException{
        return tagMapper.getTagListByTitle(title);
    }
    /**
     * 获取某标签下的问题列表
     * @param id 标签id
     * @return 问题集
     */
    public List<Question> getQuestionListByTag(int id) throws RuntimeException{
        return tagMapper.getQuestionListByTag(id);
    }
}
