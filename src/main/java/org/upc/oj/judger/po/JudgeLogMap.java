package org.upc.oj.judger.po;

import com.alibaba.fastjson.annotation.JSONField;

public class JudgeLogMap {
    @JSONField(name = "id")
    private int id;
    @JSONField(name = "username")
    private String username;
    @JSONField(name = "qid")
    private int qid;
    @JSONField(name = "result")
    private String result;
    @JSONField(name = "time_cut")
    private long time_cut;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getQid() {
        return qid;
    }

    public void setQid(int qid) {
        this.qid = qid;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public long getTime_cut() {
        return time_cut;
    }

    public void setTime_cut(long time_cut) {
        this.time_cut = time_cut;
    }
}
