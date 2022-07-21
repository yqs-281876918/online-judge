package org.upc.oj.judger.controller;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.upc.oj.judger.dao.DBS;
import org.upc.oj.judger.po.JudgeLogMap;
import org.upc.oj.judger.po.LogParam;

import javax.servlet.http.HttpServletResponse;
import java.util.Base64;
import java.util.List;

@RestController
public class JudgeLog {
    @Autowired
    DBS db;

    /**
     *
     * @param logParam
     * base64编码的字符串 解码后对应字典
     * {
     *     userid String    用户名/id
     *     qid    String    问题编号
     * }
     * @return
     * 返回结果为Json字符串 对应字典为  List
     * {
     *     id           int     记录编号 无关紧要
     *     username     String  用户名
     *     qid          int     问题编号
     *     result       Json    记录字符串，该值与测评后返回的测评结果一致
     *     time_cut     int     提交测评的时间戳
     * }
     */
    @CrossOrigin
    @RequestMapping("/judgelog")
    public String Judge(@RequestParam("logParam") String logParam, HttpServletResponse response ){
        Base64.Decoder base64_decoder = Base64.getDecoder();
        LogParam log= JSON.parseObject(new String(base64_decoder.decode(logParam)),LogParam.class);
        List<JudgeLogMap> map=db.GetJudgeLog(log.getUserid(),log.getQid());
        System.out.println(logParam);
        return JSON.toJSONString(map);
    }
}
