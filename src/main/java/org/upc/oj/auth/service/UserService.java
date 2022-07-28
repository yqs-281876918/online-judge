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


    /**
     * 判断用户是存在
     * @param username 用户名
     * @return
     */
    public List<OJUser> getUserByName(String username) throws RuntimeException{
        return userMapper.getUserByName(username);
    }

    /**
     * 增加用户
     * @param user 用户对象
     * @return
     */
    public int addUser(OJUser user) throws RuntimeException{
        return userMapper.addUser(user);
    }

    /**
     * 修改用户信息
     * @param user 用户对象
     * @param username 要修改的用户
     * @return
     */
    public int updateUser(OJUser user,String username) throws RuntimeException{
        return userMapper.updateUser(user,username);
    }
}
