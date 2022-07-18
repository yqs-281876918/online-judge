package org.upc.oj.auth.po;

import lombok.Data;
import lombok.ToString;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Data
@ToString
public class OnlineJudgeToken {
    private String userName;//用户名
    private Long createAt;//创建时间
    private Long expireAt;//到期时间
    private String identity;//身份
    private Map<String, String> extra = new HashMap<>();//额外信息

    public static long MILLI_SECOND = 1L;//毫秒
    public static long SECOND = MILLI_SECOND * 60;//秒
    public static long MINUTE = SECOND * 60;//分
    public static long HOUR = MINUTE * 60;//时
    public static long DAY = HOUR * 24;//天
    public static long WEEK = DAY * 7;//周
    public static long MONTH = DAY * 30;//月
    public static long YEAR = MONTH * 12;//年

    /**
     * 创建token对象
     *
     * @param lifeTime 存活时间(毫秒)
     */
    public OnlineJudgeToken(long lifeTime) {
        createAt = new Date().getTime();
        expireAt = createAt + lifeTime;
    }

    /**
     * 判断该token对象是否过期
     *
     * @return false:未过期 true:过期
     */
    public boolean isExpired() {
        return (new Date().getTime() - expireAt) > 0;
    }
}
