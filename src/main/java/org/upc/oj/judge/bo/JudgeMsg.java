package org.upc.oj.judge.po;

import lombok.Getter;
import org.upc.oj.judge.config.JudgeConfig;

@Getter
public class JudgeMsg {
    private String lang;
    private Integer qid;
    private String code;
    private String input_path;
    private String output_path;
    private Integer timeout;
    private Integer memory_limit;
    private JudgeMsg(){}
    public JudgeMsg create(Integer qid,String lang,String code){
        this.lang=lang;
        this.qid=qid;
        this.code=code;
        input_path= JudgeConfig.ioCacheDir+
    }
}
