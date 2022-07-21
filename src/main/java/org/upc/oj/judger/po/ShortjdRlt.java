package org.upc.oj.judger.po;

import com.alibaba.fastjson.annotation.JSONField;

public class ShortjdRlt {
    @JSONField(name="Test_sum")
    private String Test_sum;
    @JSONField(name="Test_pass")
    private String Test_pass;
    @JSONField(name="First_error")
    private String First_error;

    public String getTest_sum() {
        return Test_sum;
    }

    public void setTest_sum(String test_sum) {
        Test_sum = test_sum;
    }

    public String getTest_pass() {
        return Test_pass;
    }

    public void setTest_pass(String test_pass) {
        Test_pass = test_pass;
    }

    public String getFirst_error() {
        return First_error;
    }

    public void setFirst_error(String first_error) {
        First_error = first_error;
    }
}
