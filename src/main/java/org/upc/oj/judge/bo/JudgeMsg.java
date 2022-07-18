package org.upc.oj.judge.bo;

import lombok.Data;
import lombok.Getter;
import org.upc.oj.judge.config.JudgeConfig;
import org.upc.oj.judge.dto.JudgeRequestParam;

import java.util.Locale;

@Data
public class JudgeMsg {
    private Integer qid;
    private String lang;
    private String code;
    private String code_path;
    private String input_path;
    private String output_path;
    private String result_path;
    private Integer timeout;
    private Integer memory_limit;
    public JudgeMsg(){}
}
