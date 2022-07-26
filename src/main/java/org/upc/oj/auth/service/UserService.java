package org.upc.oj.auth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.upc.oj.auth.dao.UserMapper;
import org.upc.oj.auth.po.OJUser;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;
    /**
     * 获取所有用户信息
     * @param user 用户对象
     * @param start 起始位置
     * @param pageSize 每页用户数量
     * @return 用户list
     */
    public List<OJUser> getUsers(OJUser user, int start, int pageSize) throws RuntimeException{
        return userMapper.getUsers(user,start,pageSize);
    }

    /**
     * 获取所有用户数量
     * @param user 用户对象
     * @return 用户数量
     */
    public int getUserCount(OJUser user) throws RuntimeException{
        return userMapper.getUserCount(user);
    }
}
