package org.upc.oj.bank.dao;

import org.apache.ibatis.annotations.Mapper;
import org.upc.oj.bank.po.Tag;

import java.util.List;

@Mapper
public interface TagMapper {
    /**
     * 增加标签
     * @param title
     * @return
     */
    public void insertTag(String title);
    /**
     * 查询所有标签
     * @param
     * @return
     */
    public List<Tag> getTagList();
    /**
     * 删除某title的标签
     * @param title
     * @return
     */
    public void delTagByTitle( String title);
    public List<Tag> getTagByTitle(String title);
}
