package org.upc.oj.judge.po;

import lombok.Data;

@Data
public class SubmitRecord {
    private Integer id;
    private String username;
    private Integer qid;
    private Integer result;
    private Integer nb_pass;
    private Integer nb_total;
    private String lang;
    private Long submitAt;
}
