package org.upc.oj.judger.controller;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ser.Serializers;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.upc.oj.judger.dao.Database;
import org.upc.oj.judger.po.*;
import org.upc.oj.judger.service.SubString;

import java.util.Base64;
import java.util.List;


@RestController
public class JudgeInterface {
    private static String root="E:\\compile-main";

    /**
     *
     * @param userid
     * @param questionid
     * @param lanTp
     * @param code
     * @return
     */
    @RequestMapping("/judger")
    public String Judge(@RequestParam("judgerParam") String judgerParam )
    {
        Judgerparam param=JSON.parseObject(judgerParam,Judgerparam.class);

       // Base64.Encoder base64_encoder = Base64.getEncoder();
        Base64.Decoder base64_decoder = Base64.getDecoder();
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
        Database db=new Database();
        db.Connect2Database();
        List<TestIOFile>testIOFiles= db.GetTestByQid(param.getQuestionid());
        db.ShutDownCon();
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




}
