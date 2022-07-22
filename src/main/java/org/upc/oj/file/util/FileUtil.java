package org.upc.oj.file.util;

import java.io.*;
import java.util.Locale;

public class FileUtil {

    public static String readFile(String path) throws Exception{
        File file=new File(path);
        InputStreamReader isr=new InputStreamReader(new FileInputStream(file));
        BufferedReader bis=new BufferedReader(isr);
        StringBuilder res = new StringBuilder();
        String line;
        while ((line=bis.readLine())!=null){
            res.append(line);
        }
        return res.toString();
    }

    public static void createDirs(String path) throws Exception {
        File file = new File(path);
        file.mkdirs();
    }

    public static void writeToFile(String filepath, String content) throws Exception {
        File file = new File(filepath);
        File dir = new File(filepath.substring(0,filepath.length()-file.getName().length()));
        dir.mkdirs();
        file.createNewFile();
        OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(filepath));
        writer.write(content);
        writer.close();
    }

    public static void writeToFileIfNotExists(String filepath, String content) throws Exception {
        File file = new File(filepath);
        if(file.exists()){
            return;
        }
        writeToFile(filepath,content);
    }


    public static boolean isDirExist(String filepath) {
        return new File(filepath).exists();
    }

    /**
     * 递归删除目录下的所有文件及子目录下所有文件
     *
     * @param file（File） 将要删除的文件目录//文件也可以
     * @return
     */

    public static void delFile(File file) {
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (File f : files) {
                if(f.isDirectory()){
                    delFile(f);
                }
                file.delete();
            }
        }else {
            file.delete();
        }
    }

}
