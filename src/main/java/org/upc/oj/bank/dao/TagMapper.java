package org.upc.oj.bank.dao;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TagMapper {
    void insertTag(String title);
}
