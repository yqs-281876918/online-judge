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
    public String createTag(String title){
        try {
            tagMapper.insertTag(title);
        }catch (Exception e){
            return e.getCause().getMessage();
        }
        return null;
    }
    /**
     * 查询所有标签
     * @param
     * @return
     */
    public List<Tag> getTagList(){
        try {
            return tagMapper.getTagList();
        }catch (Exception e){
            return null;
        }
    }
    /**
     * 删除标签
     * @param title
     * @return
     */
    public String delTagByTitle(String title){
        try {
            tagMapper.delTagByTitle(title);
        }catch (Exception e){
            return e.getCause().getMessage();
        }
        return null;
    }

}
