package org.upc.oj.judge.config;

public class Language {
    public static String JAVA="Main.java";
    public static String PYTHON="judger_linux.py";
    public static String GOLANG="judger_linux.go";
    public static String getCodeFilename(String language){
        language=language.toLowerCase();
        switch (language){
            case "java":
                return JAVA;
            case "python":
                return PYTHON;
            case "golang":
                return GOLANG;
            default:
                return JAVA;
        }
    }
}
