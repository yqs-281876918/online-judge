package org.upc.oj.auth;

import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
public class OnlineJudgeToken {
    private String userName;//用户名
    private Long createAt;//创建时间
    private Long expireAt;//到期时间
    private String identity;//身份
    private Object extra;//额外信息
    public OnlineJudgeToken(){
        createAt=new Date().getTime();
    }
}
