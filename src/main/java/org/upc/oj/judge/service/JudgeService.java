package org.upc.oj.judge.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.upc.oj.file.util.FileUtil;
import org.upc.oj.judge.bo.JudgeMsg;
import org.upc.oj.judge.config.JudgeConfig;
import org.upc.oj.judge.dao.JudgeMapper;
import org.upc.oj.judge.po.QuestionIO;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class JudgeService {
    @Autowired
    private JudgeMapper judgeMapper;
    @Autowired
    private JudgeConfig config;

    public Map<String, Object> judge(JudgeMsg judgeMsg){
        Map<String,Object> msg = new HashMap<>();
        try {
            //如果没有输入输出的缓存文件,就从数据库里读取并缓存
            checkAndCreateIOFile(judgeMsg);
            //将代码写成文件
            FileUtil.writeToFile(judgeMsg.getCode_path(),judgeMsg.getCode());
            //开始评测
            ProcessBuilder judgeExeBuilder = new ProcessBuilder();
            String[] commands = new String[]{config.judgeKernelPath, "-lang", judgeMsg.getLang(), "-code",
                    judgeMsg.getCode_path(),"-input",judgeMsg.getInput_path(),"-output",judgeMsg.getOutput_path(),
                    "-result",judgeMsg.getResult_path()};
            judgeExeBuilder.command(String.join(" ",commands));
            judgeExeBuilder.start();
            msg.put("status","judging");
            msg.put("description","评测提交成功");
            return msg;
        }catch (Exception e){
            msg.put("status","failed");
            msg.put("description","评测系统出错");
            return msg;
        }
    }

    private void checkAndCreateIOFile(JudgeMsg judgeMsg) throws Exception {
        if (FileUtil.isDirExist(judgeMsg.getInput_path())&&FileUtil.isDirExist(judgeMsg.getOutput_path())){
            return;
        }
        FileUtil.createDirs(judgeMsg.getInput_path());
        FileUtil.createDirs(judgeMsg.getOutput_path());
        List<QuestionIO> questionIoList = judgeMapper.queryQuestionIO(judgeMsg.getQid());
        for (int i = 0; i < questionIoList.size(); i++) {
            QuestionIO data = questionIoList.get(i);
            String inputFilePath = judgeMsg.getInput_path() + i + ".in";
            String outputFilePath = judgeMsg.getOutput_path() + i + ".out";
            FileUtil.writeToFile(inputFilePath,data.getInput());
            FileUtil.writeToFile(outputFilePath,data.getOutput());
        }
    }
}
