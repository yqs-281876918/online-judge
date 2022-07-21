package org.upc.oj.judger.po;

import com.alibaba.fastjson.annotation.JSONField;



public class Judgerparam {
    @JSONField(name = "userid")
    private String userid;
    @JSONField(name = "questionid")
    private String questionid;
    @JSONField(name = "lanTp")
    private String lanTp;
    @JSONField(name = "code")
    private String code;

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getQuestionid() {
        return questionid;
    }

    public void setQuestionid(String questionid) {
        this.questionid = questionid;
    }

    public String getLanTp() {
        return lanTp;
    }

    public void setLanTp(String lanTp) {
        this.lanTp = lanTp;
    }

    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
}
