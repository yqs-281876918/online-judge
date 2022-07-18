package org.upc.oj.judger.po;

import com.alibaba.fastjson.annotation.JSONField;

public class Information {
    @JSONField(name = "code")
    private int code;
    @JSONField(name = "data")
    private String data;
    @JSONField(name = "info")
    private String info;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
