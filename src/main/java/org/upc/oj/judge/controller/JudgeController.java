package org.upc.oj.judge.controller;

import com.mysql.cj.util.Base64Decoder;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.upc.oj.auth.interceptor.wrapper.AuthedHttpServletRequest;
import org.upc.oj.bank.po.Question;
import org.upc.oj.bank.service.QuestionService;
import org.upc.oj.judge.bo.JudgeMsg;
import org.upc.oj.judge.config.JudgeConfig;
import org.upc.oj.judge.config.JudgeErr;
import org.upc.oj.judge.config.Language;
import org.upc.oj.judge.dao.JudgeMapper;
import org.upc.oj.judge.dao.SubmitRecordMapper;
import org.upc.oj.judge.dto.JudgeRequestParam;
import org.upc.oj.judge.po.SubmitRecord;
import org.upc.oj.judge.service.JudgeService;
import org.upc.oj.judge.service.SubmitRecordService;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Date;
import java.util.Map;

@RestController
@RequestMapping("judge")
public class JudgeController {
    @Autowired
    private JudgeService judgeService;
    @Autowired
    private SubmitRecordMapper submitRecordMapper;
    @Autowired
    private JudgeConfig config;
    @Autowired
    private QuestionService questionService;
    @Autowired
    private JudgeMapper judgeMapper;

    @PostMapping("/code")
    public Map<String, Object> judge(@RequestBody JudgeRequestParam param,AuthedHttpServletRequest request) {
        long submitAt=new Date().getTime();

        Base64.Decoder base64_decoder = Base64.getDecoder();
        param.setCode(new String(base64_decoder.decode(param.getCode().getBytes(StandardCharsets.UTF_8)),StandardCharsets.UTF_8));
        Map<String,Object> res =judgeService.judge(param.getCode(),param.getLang(),param.getQid());

        SubmitRecord record=new SubmitRecord();
        record.setUsername(request.getUsername());
        record.setQid(param.getQid());
        if(res.get("status").equals("pass")) {
            record.setResult(0);
        }else {
            Map<String,Object> msg=(Map<String, Object>) res.get("msg");
            record.setResult(JudgeErr.getErrIDByTypeStr((String) msg.get("msg_type")));
        }
        record.setNb_pass((Integer) res.get("pass"));
        record.setNb_total((Integer) res.get("total"));
        record.setLang(param.getLang());
        record.setSubmitAt(submitAt);
        submitRecordMapper.insert(record);
        return res;
    }

}
