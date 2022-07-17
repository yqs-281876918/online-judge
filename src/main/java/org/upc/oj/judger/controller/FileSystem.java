package org.upc.oj.judger.controller;

import org.upc.oj.judger.po.FilePathInfo;

import java.io.File;
import java.io.FileNotFoundException;


public class FileSystem {
    /**
     *
     * @param info
     * @return  返回值为整数型，
     * 0：所有目录创建成功！
     * 1：根目录创建失败！
     * 2：代码目录创建失败！
     * 3：输入用例目录创建失败！
     * 4：输出用例目录创建失败！
     * 5：测试结果目录创建失败！
     */
    public int  CreateDir(FilePathInfo info)  {
        File f=new File(info.getPath_workSpace());
        if(!f.mkdir())return 1;
        f=new File(info.getPath_code());
        if(!f.mkdir())return 2;
        f=new File(info.getPath_in());
        if(!f.mkdir())return 3;
        f=new File(info.getPath_out());
        if(!f.mkdir())return 4;
        f=new File(info.getPath_result());
        if(!f.mkdir())return 5;
        return 0;
    }


}
