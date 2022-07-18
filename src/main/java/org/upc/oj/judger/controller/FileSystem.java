package org.upc.oj.judger.controller;

import org.upc.oj.judger.po.FilePathInfo;
import org.upc.oj.judger.po.TestIOFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;


public class FileSystem {
    FilePathInfo info=null;
    List<TestIOFile> testFiles=null;
    private String Code;
    public FileSystem(FilePathInfo info ,List<TestIOFile> testFiles,String Code){
        this.info=info;
        this.testFiles=testFiles;
        this.Code=Code;

    }

    /**
     *
     *
     * @return  返回值为整数型，
     * 0：所有目录创建成功！
     * 1：根目录创建失败！
     * 2：代码目录创建失败！
     * 3：输入用例目录创建失败！
     * 4：输出用例目录创建失败！
     * 5：测试结果目录创建失败！
     * 6: 目录信息有误！
     * 7: 创建工作空间根目录失败！
     */
    public int  CreateDir()  {

        if (info==null)return 6;
        File f=new File(info.getPath_root());
        if(!f.mkdir()&&!f.exists())return 7;
        f=new File(info.getPath_workSpace());
        if(!f.mkdir()&&!f.exists())return 1;
        f=new File(info.getPath_code());
        if(!f.mkdir()&&!f.exists())return 2;
        f=new File(info.getPath_in());
        if(!f.mkdir()&&!f.exists())return 3;
        f=new File(info.getPath_out());
        if(!f.mkdir()&&!f.exists())return 4;
        f=new File(info.getPath_result());
        if(!f.mkdir()&&!f.exists())return 5;
        return 0;
    }
    public int CreateUserFiles(){
        if(testFiles==null)return 1;
        File f;
        FileOutputStream out;
        f=new File(info.getPath_code()+"\\main."+info.getType_Code());
        try {
            out=new FileOutputStream(f);
            out.write(Code.getBytes());
            out.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        for(TestIOFile tp:testFiles){

            try {
                f=new File(info.getPath_in()+"\\"+tp.getId()+".in");
                out=new FileOutputStream(f);
                out.write(tp.getInnput().getBytes());
                out.close();
                f=new File(info.getPath_out()+"\\"+tp.getId()+".out");
                out=new FileOutputStream(f);
                out.write(tp.getOutput().getBytes());
                out.close();

            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }

        return 0;
    }
    /**
     * 递归删除目录下的所有文件及子目录下所有文件
     * @param file（File） 将要删除的文件目录//文件也可以
     * @return boolean Returns "true" if all deletions were successful.
     *                 If a deletion fails, the method stops attempting to
     *                 delete and returns "false".
     */

    boolean delFile(File file) {
//        if (!file.exists()) {
//            return false;
//        }
        //System.out.println(file.getAbsolutePath());
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (File f : files) {
               // System.out.print("--");
                boolean rst=delFile(f);
                if(!rst)
                {
                    System.out.println("DeleteFailed:"+f.getAbsolutePath());
                    return rst;
                }

            }
        }
        System.out.println("deleted:"+file.getAbsolutePath());

        return file.delete();
    }

    /**
     *
     * @return 返回删除信息
     * 0： 删除成功！
     * 1： 删除失败！
     *
     */
    public int DeleteAll(){
        File f=new File(info.getPath_workSpace());
        if(!delFile(f))return 1;
        return 0;
    }

}
