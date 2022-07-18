package org.upc.oj.bank.dao;

import org.apache.ibatis.annotations.Mapper;
import org.upc.oj.bank.po.Question;
import org.upc.oj.bank.po.Tag;

import java.util.List;

@Mapper
public interface TagMapper {
    /**
     * 增加标签
     * @param title 标签名称
     * @return
     */
    public int insertTag(String title);
    /**
     * 查询所有标签
     * @param
     * @return 标签集
     */
    public List<Tag> getTagList();
    /**
     * 删除标签
     * @param ids 标签id集
     * @return
     */
    public int delTagByTitle( List<Integer> ids);

    /**
     * 修改某标签名称
     * @param id,newTitle 标签id，新标签名
     * @return
     */
    public int updateTagById(int id,String newTitle);

    /**
     * 查询某标签
     * @param title 标签名
     * @return 标签集
     */
    public List<Tag> getTagListByTitle(String title);

    /**
     * 获取某标签下的问题列表
     * @param id 标签id
     * @return 问题集
     */
    public List<Question> getQuestionListByTag(int id);



}
