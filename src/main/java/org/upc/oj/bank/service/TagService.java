package org.upc.oj.bank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.upc.oj.bank.dao.TagMapper;

@Service
public class TagService {

    @Autowired
    private TagMapper tagMapper;

    public String createTag(String title){
        try {
            tagMapper.insertTag(title);
        }catch (Exception e){
            return e.getCause().getMessage();
        }
        return null;
    }
}
