package org.upc.oj.file.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

public class FileUtil {

    public static void createDirs(String path) throws Exception {
        File file = new File(path);
        file.mkdirs();
    }

    public static void writeToFile(String filepath, String content) throws Exception {
        File file = new File(filepath);
        if(file.isDirectory()){
            return;
        }
        file.createNewFile();
        OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(filepath));
        writer.write(content);
        writer.close();
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
