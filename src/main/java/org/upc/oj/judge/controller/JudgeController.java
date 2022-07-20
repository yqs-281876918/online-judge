package org.upc.oj.judge.controller;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.upc.oj.auth.interceptor.wrapper.AuthedHttpServletRequest;
import org.upc.oj.bank.po.Question;
import org.upc.oj.bank.service.QuestionService;
import org.upc.oj.judge.bo.JudgeMsg;
import org.upc.oj.judge.config.JudgeConfig;
import org.upc.oj.judge.config.Language;
import org.upc.oj.judge.dao.JudgeMapper;
import org.upc.oj.judge.dto.JudgeRequestParam;
import org.upc.oj.judge.service.JudgeService;

import java.util.Map;

@RestController
@RequestMapping("judge")
public class JudgeController {
    @Autowired
    private JudgeService judgeService;
    @Autowired
    private JudgeConfig config;
    @Autowired
    private QuestionService questionService;
    @Autowired
    private JudgeMapper judgeMapper;

    @PostMapping("/code")
    public Map<String, Object> judge(@RequestBody JudgeRequestParam param) {
        JudgeMsg judgeMsg = new JudgeMsg();
        judgeMsg.setQid(param.getQid());
        judgeMsg.setLang(param.getLangType().toLowerCase());
        judgeMsg.setCode(param.getCode());
        judgeMsg.setCode_path(config.workDir + param.getUsername() + "/" + param.getQid() + "/" + Language.getCodeFilename(param.getLangType()));
        judgeMsg.setInput_path(config.ioCacheDir + param.getQid() + "/" + config.inputDir);
        judgeMsg.setOutput_path(config.ioCacheDir + param.getQid() + "/" + config.outputDir);
        judgeMsg.setResult_path(config.workDir + param.getUsername() + "/" + param.getQid() + "/");
        Question question = judgeMapper.queryQuestion(param.getQid());
        judgeMsg.setTimeout(question.getTimeout());
        judgeMsg.setMemory_limit(question.getMemory_limit());
        return judgeService.judge(judgeMsg);
    }

    @GetMapping("/test")
    public String name(AuthedHttpServletRequest request){
        return "嗨嗨嗨";
    }

}
