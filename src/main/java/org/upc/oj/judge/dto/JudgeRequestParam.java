package org.upc.oj.judge.dto;

import lombok.Data;

@Data
public class JudgeRequestParam {
    private Integer qid;
    private String code;
    private String langType;
}
