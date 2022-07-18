package org.upc.oj.judger.service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.apache.logging.log4j.util.Strings.isBlank;

public class SubString {
    public static String cutString(String str, String start, String end) {
        try{
            if (isBlank(str)) {
                return str;
            }
            String reg= start + "(.*)" + end;
            Pattern pattern = Pattern.compile(reg);
            Matcher matcher = pattern.matcher(str);
            while (matcher.find()) {
                str = matcher.group(1);
            }
        } catch(Exception e){

        }
        return str;
    }
}
