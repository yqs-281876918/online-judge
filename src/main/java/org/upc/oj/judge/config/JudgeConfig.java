package org.upc.oj.judge.config;

import org.springframework.stereotype.Component;

@Component
public class JudgeConfig {
    public String ioCacheDir="D:/upc/summer2022/cache/";//用于缓存从数据库读出来的输入输出数据
    public String inputCacheDir=ioCacheDir+"input/";
    public String outputCacheDir=ioCacheDir+"output/";
    public String workDir="D:/upc/summer2022/workspace/";//用于指定编译器工作路径
    public String judgerFileName="judger.exe";//测评程序路径
}
