package org.upc.oj.judge.config;

import org.springframework.stereotype.Component;

import java.io.*;

@Component
public class JudgeConfig {
    public JudgeConfig(){
        String tempDir = System.getProperty("java.io.tmpdir");
        ioCacheDir=tempDir+"oj_io_cache\\";
        inputCacheDir=ioCacheDir+"input\\";
        outputCacheDir=ioCacheDir+"output\\";
        workDir=tempDir+"oj_workspace\\";
        judgerPath=workDir+"judger.exe";
        try {
            File dir = new File(ioCacheDir);
            if(!dir.exists()){
                dir.mkdirs();
            }
            dir=new File(workDir);
            if(!dir.exists()){
                dir.mkdirs();
            }
            File judgerExe=new File(judgerPath);
            if(judgerExe.exists()){
                judgerExe.delete();
            }
            judgerExe.createNewFile();
            FileOutputStream fos = new FileOutputStream(judgerExe);
            InputStream resourceAsStream = this.getClass().getClassLoader().getResourceAsStream("bin\\judger.exe");
            byte[] buffer = new byte[1024];
            int n;
            while ((n= resourceAsStream.read(buffer))!=-1){
                fos.write(buffer,0,n);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public String ioCacheDir;//用于缓存从数据库读出来的输入输出数据
    public String inputCacheDir;
    public String outputCacheDir;
    public String workDir;//用于指定编译器工作路径
    public String judgerPath;//测评程序路径
}
