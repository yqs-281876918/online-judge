package org.upc.oj.judge.config;

public class LanguageSuffix {
    public static String JAVA=".java";
    public static String PYTHON=".py";
    public static String GOLANG=".go";
    public static String getSuffix(String language){
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
