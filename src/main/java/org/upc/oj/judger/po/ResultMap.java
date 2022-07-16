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
    @JSONField(name = "memory_cost")
    private  float memory_cost;
    @JSONField(name = "expect_output")
    private String expect_output;
    @JSONField(name = "actual_output")
    private String actual_output;
    @JSONField(name = "detail")
    private  String detail;

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getExpect_output() {
        return expect_output;
    }

    public void setExpect_output(String expect_output) {
        this.expect_output = expect_output;
    }

    public String getActual_output() {
        return actual_output;
    }

    public void setActual_output(String actual_output) {
        this.actual_output = actual_output;
    }

    public float getMemory_cost() {
        return memory_cost;
    }

    public void setMemory_cost(float memory_cost) {
        this.memory_cost = memory_cost;
    }

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

    @Override
    public String toString() {
        return "ResultMap{" +
                "msg_type='" + msg_type + '\'' +
                ", description='" + description + '\'' +
                ", time_cost=" + time_cost +
                ", TestId='" + TestId + '\'' +
                ", memory_cost=" + memory_cost +
                ", expect_output='" + expect_output + '\'' +
                ", actual_output='" + actual_output + '\'' +
                ", detail='" + detail + '\'' +
                '}';
    }
}
