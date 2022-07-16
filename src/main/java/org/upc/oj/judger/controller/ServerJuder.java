package org.upc.oj.judger.controller;

import com.alibaba.fastjson.JSON;
import com.mysql.cj.Session;
import org.upc.oj.judger.po.JudgeMsg;
import org.upc.oj.judger.po.ResultMap;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;


public class ServerJuder {
    private String cmd;
    private JudgeMsg Msg;
    public ServerJuder(JudgeMsg msg){
         Msg=msg;
         cmd  = "judgerpath " +
                "-lang=langs " +
                "-input=inputpath\\inputs.in " +
                "-output=outputpath\\outputs.out " +
                "-result=resultpath\\results.json " +
                "-code=codepath\\codes.java " +
                "-timeout=timeouts " +
                "-memory-limit=memlimits";
         cmd=cmd.replace("langs",msg.getLang());
         cmd=cmd.replace("judgerpath",msg.getPath_Judger());
         cmd=cmd.replace("inputpath",msg.getPath_Input());
         cmd=cmd.replace("outputpath",msg.getPath_Output());
         cmd=cmd.replace("codepath",msg.getPath_Code());
         cmd=cmd.replace("timeouts","5000");
         cmd=cmd.replace("memlimits","256");
    }


    public List<ResultMap> Start() throws IOException, InterruptedException {
        String orangestr="E:\\compile-main\\judger.exe -lang=java -input=E:\\compile-main\\in\\inputs.in -output=E:\\compile-main\\out\\outputs.out -result=E:\\compile-main\\results.json -code=E:\\compile-main\\App.java -timeout=5000 -memory-limit=256";

        for(Integer i=1;i<=Test.l.size();++i){

            Process pid=Runtime.getRuntime().
                    exec(orangestr.
                            replace("inputs",i.toString()).
                            replace("outputs",i.toString()).
                            replace("results",i.toString()));
            System.out.println("执行编译器");
            pid.waitFor();
            System.out.println("运行结束");
            File f=new File("E:\\compile-main\\"+i.toString()+".json");
            FileInputStream in=new FileInputStream(f);
            byte b[]=null;
            b=in.readAllBytes();
            System.out.println(new String(b));
            //System.out.println(f.toString());输出的是文件历经
            in.close();
        }



        return null;
    }

}
