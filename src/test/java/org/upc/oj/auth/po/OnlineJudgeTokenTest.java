package org.upc.oj.auth.po;

import com.alibaba.fastjson.JSON;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class OnlineJudgeTokenTest {
    @Test
    public void test() {
        String jsonStr="{\"userName\":\"yqs281876918\"}";
        OnlineJudgeToken onlineJudgeToken = JSON.parseObject(jsonStr, OnlineJudgeToken.class);
        System.out.println(onlineJudgeToken);

    }
}