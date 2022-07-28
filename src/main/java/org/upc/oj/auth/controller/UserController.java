package org.upc.oj.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.upc.oj.auth.interceptor.wrapper.AuthedHttpServletRequest;
import org.upc.oj.auth.po.OJUser;
import org.upc.oj.auth.service.UserService;
import org.upc.oj.auth.util.AuthUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController()
@RequestMapping("/auth")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired(required = false)
    private AuthUtil authUtil;

    @GetMapping("/users")
    public Map<String,Object> getUsers(OJUser user, int curPage, int pageSize, AuthedHttpServletRequest respond){
        Map<String,Object> map=new HashMap<>();
        if(respond.getIdentity().equals("admin")) {
            try{
                int start=(curPage-1)*pageSize;
                List<OJUser> userList=userService.getUsers(user,start,pageSize);
                for(OJUser item : userList){
                    item.setPassword(authUtil.decodePassword(item.getPassword()));
                }
                int totalCount=userService.getUserCount(user);
                map.put("status","success");
                map.put("userList",userList);
                map.put("count",userList.size());//该页数量
                map.put("totalCount",totalCount);//符合某条件用户总数量
                map.put("pageCount",totalCount%pageSize==0?totalCount/pageSize:totalCount/pageSize+1);//一共多少页
                map.put("curPage",curPage);//目前页数
                map.put("pageSize",pageSize);//每页数量
                map.put("msg","查询成功");
                return  map;
            }catch (RuntimeException e){
                map.put("status","error");
                map.put("userList",null);
                map.put("count",0);
                map.put("totalCount",0);
                map.put("pageCount",0);
                map.put("curPage",curPage);
                map.put("pageSize",pageSize);
                map.put("msg",e.getCause().getMessage());
                return map;
            }
        }else{
            map.put("status","refused");
            map.put("msg","仅管理员才有权限查看所有用户信息");
            return map;
        }
    }

    @GetMapping("/user")
    public Map<String,Object> getUserByName(String username, AuthedHttpServletRequest respond){
        Map<String,Object> map=new HashMap<>();
        if(respond.getIdentity().equals("admin")) {
            try{
                List<OJUser> userList=userService.getUserByName(username);
                for(OJUser item : userList){
                    item.setPassword(authUtil.decodePassword(item.getPassword()));
                }
                map.put("status","success");

                map.put("msg","查询成功");
                return  map;
            }catch (RuntimeException e){
                map.put("status","error");
                map.put("userList",null);
                map.put("msg",e.getCause().getMessage());
                return map;
            }
        }else{
            map.put("status","refused");
            map.put("msg","仅管理员才有权限查看所有用户信息");
            return map;
        }
    }

    @PostMapping("/user")
    public Map<String,Object> getUsers(OJUser user, AuthedHttpServletRequest respond) {
        Map<String, Object> map = new HashMap<>();
        try {
            List<OJUser> userList = userService.getUserByName(user.getUsername());
            if (userList.size() > 0) {//用户名已存在
                map.put("status", "error");
                map.put("msg", "用户名已存在");
                return map;
            } else {
                if (respond.getIdentity().equals("admin")) {
                    user.setPassword(authUtil.encodePassword(user.getPassword()));//加密密码
                    int addCount = userService.addUser(user);
                    map.put("status", "success");
                    map.put("addCount", addCount);
                    map.put("msg", "删除成功");
                    return map;
                } else {
                    map.put("status", "refused");
                    map.put("msg", "仅管理员才有权限操作用户信息");
                    return map;
                }
            }
        } catch (RuntimeException e) {
            map.put("status", "error");
            map.put("addCount", 0);
            map.put("msg", e.getCause().getMessage());
            return map;
        }
    }

    @PatchMapping("/user/username")
    public Map<String,Object> updateUsername(OJUser user,String uid, AuthedHttpServletRequest respond) {
        Map<String, Object> map = new HashMap<>();
        try {
            List<OJUser> userList = userService.getUserByName(uid);
            if(userList.size()<=0){//用户不存在
                map.put("status", "error");
                map.put("msg", "用户不存在");
                return map;
            }else{
                userList = userService.getUserByName(user.getUsername());
                if (userList.size() > 0) {//用户名已存在
                    map.put("status", "error");
                    map.put("msg", "该用户名已存在");
                    return map;
                } else {
                    if (respond.getIdentity().equals("admin")) {
                        if(user.getPassword()!=null){
                            user.setPassword(authUtil.encodePassword(user.getPassword()));//加密密码
                        }
                        int updateCount = userService.updateUser(user,uid);
                        map.put("status", "success");
                        map.put("updateCount", updateCount);
                        map.put("msg", "修改成功");
                        return map;
                    } else {
                        map.put("status", "refused");
                        map.put("msg", "仅管理员才有权限操作用户信息");
                        return map;
                    }
                }
            }

        } catch (RuntimeException e) {
            map.put("status", "error");
            map.put("updateCount", 0);
            map.put("msg", e.getCause().getMessage());
            return map;
        }
    }

    @PatchMapping("/user/password")
    public Map<String,Object> updatePassword(OJUser user,String uid, String psw,AuthedHttpServletRequest respond) {
        Map<String, Object> map = new HashMap<>();
        try {
            List<OJUser> userList = userService.getUserByName(uid);
            if(userList.size()<=0){//用户不存在
                map.put("status", "error");
                map.put("msg", "用户不存在");
                return map;
            }else{//用户存在
                if(userList.get(0).getPassword().equals(authUtil.encodePassword(psw))){//接口中有密码，且与数据库一致
                    if (respond.getIdentity().equals("admin")) {
                        user.setPassword(authUtil.encodePassword(user.getPassword()));//加密密码
                        int updateCount = userService.updateUser(user,uid);
                        map.put("status", "success");
                        map.put("updateCount", updateCount);
                        map.put("msg", "修改成功");
                        return map;
                    } else {
                        map.put("status", "refused");
                        map.put("msg", "仅管理员才有权限操作用户信息");
                        return map;
                    }
                }else{
                    map.put("status", "error");
                    map.put("msg", "原密码错误");
                    return map;
                }
            }
        } catch (RuntimeException e) {
            map.put("status", "error");
            map.put("updateCount", 0);
            map.put("msg", e.getCause().getMessage());
            return map;
        }
    }

    @DeleteMapping("/user")
    public Map<String,Object> deleteUser(String username,AuthedHttpServletRequest respond) {
        Map<String, Object> map = new HashMap<>();
        try {
            List<OJUser> userList = userService.getUserByName(username);
            if (userList.size() <= 0) {//用户不存在
                map.put("status", "error");
                map.put("msg", "用户不存在");
                return map;
            } else {//用户存在
                if (respond.getIdentity().equals("admin")) {
                    int delCount = userService.deleteUser(username);
                    map.put("status", "success");
                    map.put("delCount", delCount);
                    map.put("msg", "删除成功");
                    return map;
                } else {
                    map.put("status", "refused");
                    map.put("msg", "仅管理员才有权限操作用户信息");
                    return map;
                }
            }
            } catch(RuntimeException e){
                map.put("status", "error");
                map.put("delCount", 0);
                map.put("msg", e.getCause().getMessage());
                return map;
            }
        }
}
