package org.upc.oj.judger.po;

import com.alibaba.fastjson.annotation.JSONField;
import com.mysql.cj.Session;

public class JudgeMsg {
    @JSONField(name = "lang")
    private String lang;
    @JSONField(name = "Question")
    private String Question;
    @JSONField(name = "UserId")
    private String UserId;
    @JSONField(name = "count")
    private String count;
    @JSONField(name = "session")
    private Session session;

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public String getQuestion() {
        return Question;
    }

    public void setQuestion(String question) {
        Question = question;
    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }


    public JudgeMsg( String lang,
                        String Question,
                        String UserId,
                        String count,
                        Session session){
        this.lang=lang;
        this.Question=Question;
        this.UserId=UserId;
        this.count=count;
        this.session=session;
    }
}
