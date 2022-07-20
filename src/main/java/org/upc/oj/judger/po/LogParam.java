package org.upc.oj.judger.po;

import com.alibaba.fastjson.annotation.JSONField;

public class LogParam {
    @JSONField(name = "userid")
    private String userid;
    @JSONField(name = "qid")
    private String qid;

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getQid() {
        return qid;
    }

    public void setQid(String qid) {
        this.qid = qid;
    }
}
