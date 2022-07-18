package org.upc.oj.judger.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.upc.oj.judger.config.JudgeConfig;
import org.upc.oj.judger.dao.JudgeMapper;
import org.upc.oj.judger.po.IOData;

import java.io.*;
import java.util.List;
import java.util.Map;

@Service
public class JudgeService {
    @Autowired
    private JudgeMapper judgeMapper;
    @Autowired
    private JudgeConfig config;
    public Map<String,Object> judge(Integer qid, String code,String username){
        checkAndCreateIOFile(qid);//如果没有输入输出的缓存文件,就从数据库里读取并缓存
        ProcessBuilder compileJava = new ProcessBuilder();
        String commands=
        compileJava.command("javac", path + "/Main.java");
        Process compile_app = compileJava.start();
        compile_app.waitFor();
    }
    private void checkAndCreateIOFile(Integer qid){
        String inputPath=config.ioCacheDir+qid+"/"+config.inputDir;
        String outputPath=config.ioCacheDir+qid+"/"+config.outputDir;
        File inputDirFile=new File(inputPath);
        File outputDirFile=new File(outputPath);
        if(inputDirFile.exists()&&inputDirFile.isDirectory()&&outputDirFile.exists()&&outputDirFile.exists()){
            return;
        }
        inputDirFile.mkdirs();
        outputDirFile.mkdirs();
        List<IOData> ioDataList = judgeMapper.queryIOData(qid);
        try {
            for(int i=0;i<ioDataList.size();i++){
                IOData data = ioDataList.get(i);
                File inputFile=new File(inputPath+i+".in");
                File outputFile=new File(outputPath+i+".out");
                OutputStreamWriter inputWriter=new OutputStreamWriter(new FileOutputStream(inputFile));
                OutputStreamWriter outputWriter=new OutputStreamWriter(new FileOutputStream(outputFile));
                inputWriter.write(data.getInput());
                outputWriter.write(data.getOutput());
                inputWriter.close();
                outputWriter.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private String createCommands()
}
