package org.upc.oj.bank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.upc.oj.bank.dao.TagMapper;
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
    public void createTag(String title) throws RuntimeException{
        tagMapper.insertTag(title);
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
     * @param title
     * @return
     */
    public void delTagByTitle(String title) throws RuntimeException{
        tagMapper.delTagByTitle(title);
    }

}
