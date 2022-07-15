package org.upc.oj.judger.po;

import com.alibaba.fastjson.annotation.JSONField;
import com.mysql.cj.Session;

public class JudgeMsg {
    @JSONField(name = "lang")
    private String lang;
    @JSONField(name = "QuestionId")
    private String QuestionId;
    @JSONField(name = "UserId")
    private String UserId;
    @JSONField(name = "Code")
    private String Path_Code;
    private String Path_Judger="";
    private String Path_Input;
    private String Path_Output;
    private String Path_Result;
    private String TimeLimit="1000";
    private String MemLimit="256";


    public String getTimeLimit() {
        return TimeLimit;
    }

    public void setTimeLimit(String timeLimit) {
        TimeLimit = timeLimit;
    }

    public String getMemLimit() {
        return MemLimit;
    }

    public void setMemLimit(String memLimit) {
        MemLimit = memLimit;
    }







    public String getPath_Judger() {
        return Path_Judger;
    }

    public void setPath_Judger(String path_Judger) {
        Path_Judger = path_Judger;
    }

    public String getPath_Input() {
        return Path_Input;
    }

    public void setPath_Input(String path_Input) {
        Path_Input = path_Input;
    }

    public String getPath_Output() {
        return Path_Output;
    }

    public void setPath_Output(String path_Output) {
        Path_Output = path_Output;
    }

    public String getPath_Result() {
        return Path_Result;
    }

    public void setPath_Result(String path_Result) {
        Path_Result = path_Result;
    }


    public String getPath_Code() {
        return Path_Code;
    }

    public void setPath_Code(String code) {
        Path_Code = code;
    }


    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public String getQuestion() {
        return QuestionId ;
    }

    public void setQuestion(String question) {
        QuestionId = question;
    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public JudgeMsg(    String lang,
                        String Question,
                        String UserId,
                        String pathCode,
                        String pathInput,
                        String pathOutput,
                        String pathResult

                        ){
        this.lang=lang;
        this.QuestionId=Question;
        this.UserId=UserId;
        this.Path_Code=pathCode;
        this.Path_Input=pathInput;
        this.Path_Output=pathOutput;
        this.Path_Result=pathResult;

    }
}
