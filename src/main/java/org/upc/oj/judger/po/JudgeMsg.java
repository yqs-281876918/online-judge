package org.upc.oj.judger.po;

import com.alibaba.fastjson.annotation.JSONField;
import com.mysql.cj.Session;

import java.util.List;

public class JudgeMsg {
    @JSONField(name = "lang")
    private String lang;
    @JSONField(name = "QuestionId")
    private String QuestionId;
    @JSONField(name = "UserId")
    private String UserId;
    @JSONField(name = "Code")
    private String Code;
    private FilePathInfo filePathInfo;
    private List<TestIOFile>testIOFiles;
    public FilePathInfo getFilePathInfo() {
        return filePathInfo;
    }

    public List<TestIOFile> getTestIOFiles() {
        return testIOFiles;
    }

    public void setTestIOFiles(List<TestIOFile> testIOFiles) {
        this.testIOFiles = testIOFiles;
    }

    public void setFilePathInfo(FilePathInfo filePathInfo) {
        this.filePathInfo = filePathInfo;
    }

    /**
     *
     * 包含测试时用的默认值
     * 正式调用时请务必调用各属性的Set方法！
     *
     * Default-Values
     *
     */

    private String Path_Judger="E:\\compile-main\\judger.exe";

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




    public String getCode() {
        return Code;
    }

    public void setCode(String code) {
        Code = code;
    }


    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public String getQuestionId() {
        return QuestionId ;
    }

    public void setQuestionId(String question) {
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
                        String Code,
                        FilePathInfo filePathInfo,
                        List<TestIOFile> testIOFiles
                        /*,

                        String pathInput,
                        String pathOutput,
                        String pathResult
                        */////这三个还是不要作为Json传输的的参数为好
                        ){
        this.lang=lang;
        this.QuestionId=Question;
        this.UserId=UserId;
        this.Code=Code;
        this.filePathInfo=filePathInfo;
        this.testIOFiles=testIOFiles;


        /*
        this.Path_Input=pathInput;
        this.Path_Output=pathOutput;
        this.Path_Result=pathResult;
        */
    }
}
