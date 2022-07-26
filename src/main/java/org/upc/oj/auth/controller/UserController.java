package org.upc.oj.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.upc.oj.auth.interceptor.wrapper.AuthedHttpServletRequest;
import org.upc.oj.auth.po.OJUser;
import org.upc.oj.auth.service.UserService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController()
@RequestMapping("/auth")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public Map<String,Object> getUsers(OJUser user, int curPage, int pageSize, AuthedHttpServletRequest respond){
        Map<String,Object> map=new HashMap<>();
        if(respond.getIdentity().equals("admin")) {
            try{
                int start=(curPage-1)*pageSize;
                List<OJUser> userList=userService.getUsers(user,start,pageSize);
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
}
