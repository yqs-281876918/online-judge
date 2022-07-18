package org.upc.oj.auth.util;

import com.alibaba.fastjson.JSON;
import org.upc.oj.auth.po.OnlineJudgeToken;


public class AuthUtil {
    public static String SECURE_KEY = "7.11-7.29 in summer2022";
    private static MyCoder coder = new MyCoder();

    public static void main(String[] args) {
        OnlineJudgeToken ojt = new OnlineJudgeToken(OnlineJudgeToken.MINUTE * 5);
        ojt.setUserName("yqs");
        ojt.setIdentity("admin");
        String token = geneToken(ojt);
        System.out.println(token);
        System.out.println(parseToken(token));
        System.out.println(ojt.isExpired());
    }

    /**
     * 将token对象转成token字符串
     *
     * @param obj token对象
     * @return token字符串
     */
    public static String geneToken(OnlineJudgeToken obj) {
        try {
            return coder.encryptBASE64(JSON.toJSONString(obj), SECURE_KEY);
        } catch (Exception e) {
            e.printStackTrace();
            return "{}";
        }
    }

    /**
     * 将token字符串转成token对象
     *
     * @param token token字符串
     * @return token对象
     */
    public static OnlineJudgeToken parseToken(String token) {
        try {
            String jsonStr = coder.decryptBASE64(token, SECURE_KEY);
            return JSON.parseObject(jsonStr, OnlineJudgeToken.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
