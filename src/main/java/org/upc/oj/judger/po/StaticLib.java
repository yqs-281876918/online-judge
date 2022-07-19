package org.upc.oj.judger.po;

public class StaticLib {

    public static String JAVA="main.java";
    public static String PYTHON="main.py";
    public static String GOLANG="main.go";
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
