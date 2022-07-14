package org.upc.oj.auth.dao;

import org.apache.ibatis.annotations.Mapper;
import org.upc.oj.auth.po.OJUser;

import java.util.List;

@Mapper
public interface UserMapper {
    List<OJUser> getUserList(String username, String password);
}
