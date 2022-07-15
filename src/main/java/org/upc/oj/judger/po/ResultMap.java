package org.upc.oj.judger.po;

import com.alibaba.fastjson.annotation.JSONField;

public class ResultMap {
    @JSONField(name = "msg_type")
    private String msg_type;
    @JSONField(name = "description")
    private String description;
    @JSONField(name = "time_cost")
    private int time_cost;
    @JSONField(name = "TestId")
    private String TestId;

    public String getMsg_type() {
        return msg_type;
    }

    public void setMsg_type(String msg_type) {
        this.msg_type = msg_type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getTime_cost() {
        return time_cost;
    }

    public void setTime_cost(int time_cost) {
        this.time_cost = time_cost;
    }

    public String getTestId() {
        return TestId;
    }

    public void setTestId(String testId) {
        TestId = testId;
    }
}
