package org.upc.oj.judger.config;

import org.springframework.stereotype.Component;

@Component
public class JudgeConfig {
    public String ioCacheDir="D:/upc/summer2022/cache/";//用于缓存从数据库读出来的输入输出数据
    public String workDir="D:/upc/summer2022/workspace/";//用于指定编译器工作路径
    public String judgeKernelPath=workDir+"judger.exe";//测评程序路径
    public String inputDir="input/";
    public String outputDir="output/";
}
