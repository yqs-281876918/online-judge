package org.upc.oj.auth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.upc.oj.auth.po.OnlineJudgeToken;
import org.upc.oj.auth.dao.UserMapper;
import org.upc.oj.auth.po.OJUser;
import org.upc.oj.auth.util.AuthUtil;
import org.upc.oj.auth.util.MyCoder;

import java.util.List;

@Service
public class LoginService {
    /**
     * 编码器
     */
    private MyCoder coder = new MyCoder();
    /**
     * 存活时间
     */
    public static long lifeTime = OnlineJudgeToken.DAY;
    @Autowired
    private UserMapper userMapper;

    /**
     * 通过账户密码认证获取授权码(token)
     *
     * @param username 账号
     * @param password 密码明文
     * @return token值, 如果为null说明认证失败
     */
    public String getTokenByLogin(String username, String password) {
        String encryptedPassword;
        try {
            encryptedPassword = coder.encryptDES(password, AuthUtil.SECURE_KEY);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        List<OJUser> userList = userMapper.getUserList(username, encryptedPassword);
        if (userList.size() == 1) {
            OJUser user = userList.get(0);
            OnlineJudgeToken ojt = new OnlineJudgeToken(lifeTime);
            ojt.setUserName(username);
            ojt.setIdentity(user.getIdentity());
            return AuthUtil.geneToken(ojt);
        } else {
            return null;
        }
    }
}
