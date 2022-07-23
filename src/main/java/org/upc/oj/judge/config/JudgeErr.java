package org.upc.oj.judge.config;

public class JudgeErr {
    public static int getErrIDByTypeStr(String type){
        switch (type){
            case "pass":
                return 0;
            case "wrong-answer":
                return 1;
            case "timeout":
                return 2;
            case "out-of-memory":
                return 3;
            case "compile-failed":
                return 4;
            case "runtime-error":
                return 5;
            case "system-exception":
                return 6;
            default:
                return 6;
        }
    }
}
