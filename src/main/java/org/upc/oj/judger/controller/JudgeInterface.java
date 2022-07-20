package org.upc.oj.judger.controller;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.upc.oj.judger.dao.DBS;
import org.upc.oj.judger.po.*;
import org.upc.oj.judger.service.SubString;

import java.util.Base64;
import java.util.List;


@RestController
public class JudgeInterface {
    private static String root="E:\\compile-main";
    @Autowired
    DBS db;
    /**
     *
     * @param judgerParam
     *
     * 是一个base64编码的字符串
     * 解码后对应字典：
     * {
     *     userid：
     *           字符串 可以用用户名
     *     questionid：
     *          字符串 题目的编号
     *     lanTp：
     *          语言类型 可以为  java python golang  若为空则默认为  java
     *     code：
     *          base64编码的字符串
     *              解码后对应原本的 代码文本
     * }
     *
     *
     * @return
     * 返货结果为json字符串对应字典
     * {
     *     code：
     *          信息状态  0-正常 其他值-异常（暂时未考虑其他情况）
     *     data：
     *          包含一个json字符串 对应字典 是一个List 每个对象是一个测试用例的测试结果
     *          {
     *              msg_type
     *              description
     *              time_cost
     *              TestId
     *              memory_cost
     *              expect_output
     *              actual_output
     *              detail
     *          }
     *     info：
     * }
     */
    @RequestMapping("/judger")
    public String Judge(@RequestParam("judgerParam") String judgerParam )
    {
        Base64.Decoder base64_decoder = Base64.getDecoder();
        Judgerparam param=JSON.parseObject(new String(base64_decoder.decode(judgerParam.getBytes())),Judgerparam.class);

       // Base64.Encoder base64_encoder = Base64.getEncoder();

        String DeCode=new String(base64_decoder.decode(param.getCode()));
        System.out.println(SubString.cutString(DeCode,"class","{"));
        System.out.println("userid:"+param.getUserid());
        FilePathInfo filePathInfo=new FilePathInfo();
        filePathInfo.setPath_root(root+"\\"+param.getUserid());
        filePathInfo.setPath_workSpace(filePathInfo.getPath_root()+"\\"+param.getQuestionid());
        filePathInfo.setPath_in(filePathInfo.getPath_workSpace()+"\\in");
        filePathInfo.setPath_out(filePathInfo.getPath_workSpace()+"\\out");
        filePathInfo.setPath_result(filePathInfo.getPath_workSpace()+"\\result");
        filePathInfo.setPath_code(filePathInfo.getPath_workSpace()+"\\code");
        filePathInfo.setType_Code(param.getLanTp());
        List<TestIOFile>testIOFiles= db.GetTestByQid(param.getQuestionid());
//        Database db=new Database();
//        db.Connect2Database();
//        List<TestIOFile>testIOFiles= db.GetTestByQid(param.getQuestionid());
//        db.ShutDownCon();
        FileSystem fileSystem=new FileSystem(filePathInfo,testIOFiles,DeCode);
        fileSystem.CreateDir();
        fileSystem.CreateUserFiles();
        JudgeMsg judgeMsg=new JudgeMsg(param.getLanTp(),param.getQuestionid(),param.getUserid(),DeCode,filePathInfo,testIOFiles);
        ServerJuder js=new ServerJuder(judgeMsg);
        List<ResultMap>rlt=js.Start();
        fileSystem.DeleteAll();
        Information rtn= new Information();
        rtn.setCode(0);
        rtn.setData(JSON.toJSONString(rlt));
        return JSON.toJSONString(rtn);
    }
//


}
