package org.upc.oj.auth.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.upc.oj.auth.po.OJUser;

import java.util.List;

@Mapper
public interface UserMapper {
    List<OJUser> getUserList(String username, String password);
    List<OJUser> getUsers(OJUser user, @Param("start") int start,@Param("pageSize") int pageSize);
    int getUserCount(OJUser user);
}


