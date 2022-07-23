package org.upc.oj.judge.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.upc.oj.file.util.FileUtil;
import org.upc.oj.judge.config.JudgeConfig;
import org.upc.oj.judge.config.Language;
import org.upc.oj.judge.dao.JudgeMapper;
import org.upc.oj.judge.dao.SubmitRecordMapper;
import org.upc.oj.judge.po.QuestionIO;
import org.upc.oj.judge.po.SubmitRecord;

import java.util.*;

@Service
public class JudgeService {
    @Autowired
    private JudgeMapper judgeMapper;
    @Autowired
    private JudgeConfig config;

    public Map<String, Object> judge(String code,String type,Integer qid){
        Map<String,Object> msg = new HashMap<>();
        try {
            List<QuestionIO> ios=judgeMapper.queryQuestionIO(qid);
            //将代码写成文件
            String randWorkspace= config.workDir+UUID.randomUUID().toString()+"/";
            String codePath=randWorkspace+ Language.getCodeFilename(type);
            FileUtil.writeToFile(codePath,code);
            //开始评测
            int passCount=0;
            double totalTimeCost=0;
            double totalMemCost=0;
            for(QuestionIO io : ios){
                String inputPath=config.inputCacheDir+io.getId()+".in";
                FileUtil.writeToFileIfNotExists(inputPath,io.getInput());
                String outputPath=config.outputCacheDir+io.getId()+".out";
                FileUtil.writeToFileIfNotExists(outputPath,io.getOutput());
                String resultPath=randWorkspace+io.getId()+".json";
                String[] commands = new String[]{config.workDir+config.judgerFileName, "-lang="+type,
                        "-code="+codePath, "-input="+inputPath,"-output="+outputPath, "-result="+resultPath};
                Process process = Runtime.getRuntime().exec(String.join(" ",commands));
                process.waitFor();
                byte[] bytes = new byte[1024];
                process.getInputStream().read(bytes);
                String resultJsonStr=FileUtil.readFile(resultPath);
                JSONObject jsonObject = JSON.parseObject(resultJsonStr);
                if(!jsonObject.getString("msg_type").equals("pass")){
                    msg.put("status","fail");
                    msg.put("pass",passCount);
                    msg.put("total",ios.size());
                    msg.put("msg",jsonObject);
                    return msg;
                }
                totalTimeCost+=jsonObject.getDouble("time_cost");
                totalMemCost+=jsonObject.getDouble("memory_cost");
                passCount++;
            }
            msg.put("status","pass");
            msg.put("pass",ios.size());
            msg.put("total",ios.size());
            msg.put("avg_time_cost",totalTimeCost/ios.size());
            msg.put("avg_memory_cost",totalMemCost/ios.size());
            return msg;
        }catch (Exception e){
            e.printStackTrace();
            msg.put("status","fail");
            msg.put("description","评测系统出错");
            return msg;
        }
    }
}
