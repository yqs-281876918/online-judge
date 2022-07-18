package org.upc.oj.judger.controller;

import com.alibaba.fastjson.JSON;

import org.upc.oj.judger.po.JudgeMsg;
import org.upc.oj.judger.po.ResultMap;
import org.upc.oj.judger.po.TestIOFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
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
                "-code=codepath\\main.codetype " +
                "-timeout=timeouts " +
                "-memory-limit=memlimits";
         cmd=cmd.replace("langs",msg.getFilePathInfo().getType_Code());
         cmd=cmd.replace("judgerpath",msg.getPath_Judger());    //
         cmd=cmd.replace("inputpath",msg.getFilePathInfo().getPath_in());      //
         cmd=cmd.replace("outputpath",msg.getFilePathInfo().getPath_out());    //
         cmd=cmd.replace("codepath",msg.getFilePathInfo().getPath_code());        //
        cmd=cmd.replace("codetype",msg.getFilePathInfo().getType_Code());
         cmd=cmd.replace("resultpath",msg.getFilePathInfo().getPath_result());    //
         cmd=cmd.replace("timeouts",msg.getTimeLimit());
         cmd=cmd.replace("memlimits",msg.getTimeLimit());
    }

    /**
     *
     *
     *
     * @param
     * @return
     * @throws IOException
     * @throws InterruptedException
     */
    public List<ResultMap> Start()  {
        List<ResultMap> rtn=new ArrayList<>();
        String orangestr=cmd;//"E:\\compile-main\\judger.exe -lang=java -input=E:\\compile-main\\in\\inputs.in -output=E:\\compile-main\\out\\outputs.out -result=E:\\compile-main\\results.json -code=E:\\compile-main\\App.java -timeout=5000 -memory-limit=256";
        System.out.println(orangestr);
        try {


            for(TestIOFile i :Msg.getTestIOFiles()){
                System.out.println(i.getInnput());
                Process pid= null;

                    pid = Runtime.getRuntime().
                            exec(orangestr.
                                    replace("inputs",i.getId()).
                                    replace("outputs",i.getId()).
                                    replace("results",i.getId()));

                System.out.println("执行编译器");

                pid.waitFor();
                Runtime.getRuntime().
                        exec("taskkill /f /IM conhost.exe /t");
                System.out.println("运行结束");
                File f=new File(Msg.getFilePathInfo().getPath_result()+"\\"+i.getId()+".json");
                if (!f.exists())continue;
                FileInputStream in=new FileInputStream(f);
                byte b[]=null;
                b=in.readAllBytes();

                ResultMap res=JSON.parseObject(new String(b),ResultMap.class);
                res.setTestId(i.getId());
                rtn.add(res);
                System.out.println(JSON.toJSONString(res));
                in.close();
                //System.out.println(f.delete());
                //Time.sleep(1000);

                //System.out.println(res.toString());//测试是否生成结果集成功
                //System.out.println(new String(b));//控制台输出执行后的结果
                //System.out.println(f.toString());输出的是文件历经
                }


        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return rtn;//返回测试结果集


//        for(Integer i=1;i<=Test.l.size();++i){
//
//            Process pid=Runtime.getRuntime().
//                    exec(orangestr.
//                            replace("inputs",i.toString()).
//                            replace("outputs",i.toString()).
//                            replace("results",i.toString()));
//            System.out.println("执行编译器");
//            pid.waitFor();
//            System.out.println("运行结束");
//            File f=new File("E:\\compile-main\\"+i.toString()+".json");
//            FileInputStream in=new FileInputStream(f);
//            byte b[]=null;
//            b=in.readAllBytes();
//            System.out.println(new String(b));
//            //System.out.println(f.toString());输出的是文件历经
//            in.close();
//        }

    }

}
