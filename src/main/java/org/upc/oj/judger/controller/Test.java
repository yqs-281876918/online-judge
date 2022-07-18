package org.upc.oj.judger.controller;

import com.alibaba.fastjson.JSON;
import org.upc.oj.judger.po.FilePathInfo;
import org.upc.oj.judger.po.JudgeMsg;
import org.upc.oj.judger.po.ResultMap;
import org.upc.oj.judger.po.TestIOFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Test {
    public static List<TestIOFile> l;
    public static void  main(String[] args){
        String Code="\n" +
                "import java.util.Scanner;\n" +
                "\n" +
                "public class main \n" +
                "{\n" +
                "    public static void main( String[] args )\n" +
                "    {\n" +
                "\n" +
                "\t  Scanner cin=new Scanner(System.in);\n" +
                "String\ta=cin.next();\n" +
                "\tif(a.equals(\"1\"))while(true);\n" +
                "       \t System.out.println(a);\n" +
                "\n" +
                "\n" +
                "\n" +
                "    }\n" +
                "}";

        l=new ArrayList<>();
        TestIOFile t=new TestIOFile();

        t.setId("1");
        t.setInnput("1");
        t.setOutput("1");
        l.add(t);
        t=new TestIOFile();
        t.setId("2");
        t.setInnput("2");
        t.setOutput("3");
        l.add(t);
        t=new TestIOFile();
        t.setId("3");
        t.setInnput("344444");
        t.setOutput("3");
        l.add(t);
        t=new TestIOFile();
        t.setId("4");
        t.setInnput("455878");
        t.setOutput("455878");
        l.add(t);
        FilePathInfo info=new FilePathInfo();
        info.setPath_workSpace("E:\\compile-main\\work");
        info.setPath_code("E:\\compile-main\\work\\code");
        info.setPath_in("E:\\compile-main\\work\\in");
        info.setPath_out("E:\\compile-main\\work\\out");
        info.setPath_result("E:\\compile-main\\work\\result");
        info.setType_Code("java");
        FileSystem fs=new FileSystem(info,l,Code);
        System.out.println( fs.CreateDir());
        System.out.println(fs.CreateUserFiles());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        JudgeMsg msg=new JudgeMsg("java","App","",Code);
        msg.setPath_Code(info.getPath_code());
        msg.setPath_Input(info.getPath_in());
        msg.setPath_Output(info.getPath_out());
        msg.setPath_Result(info.getPath_result());
        ServerJuder s=new ServerJuder(msg);
        List<ResultMap>rlt;

        try {
            rlt=s.Start(l);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        try {
            Runtime.getRuntime().
                    exec("taskkill /f /IM conhost.exe /t");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        fs.DeleteAll();

        // System.out.println(fs.DeleteAll());
//
//        try {
//            System.out.println(JSON.toJSONString(new ServerJuder(new JudgeMsg("java","App","","")).Start(l)));
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }

    }
}
