package org.upc.oj.judger.dao;

import org.upc.oj.judger.po.TestIOFile;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Database {
   private Connection con;
   private Statement statement;
   private ResultSet result;

   //return Code :  0-连接成功！ 1-数据库连接失败！ 2-Statement创建失败！
   public int Connect2Database()  {
       try {
           con= DriverManager.getConnection("jdbc:mysql://sh-cynosdbmysql-grp-5t7sas8q.sql.tencentcdb.com:24769/oj?serverTimezone=UTC&useUnicode=true&characterEncoding=utf-8",
                   "own",
                   "upcJavaee.com");//连接数据库
           if(con==null)return 1;
           statement=con.createStatement();//创建数据库连接的statement
       } catch (SQLException e) {
           throw new RuntimeException(e);
       }

       if (statement==null)return 2;
        return 0;//数据库正常连接并且statement创建成功
   }
   //回去对应题目的输入和输出用例，并以List返回，执行此操作前请首先执行Connect2Database（）
   public List<TestIOFile> GetTestByQid(String Qid)  {
       List<TestIOFile> RtnRes=new ArrayList<>();
       ResultSet res=null;
       String sql="select * from question_io where qid="+Qid+";";
       try {
           res=statement.executeQuery(sql);
           while (res.next()){
               TestIOFile add=new TestIOFile();
               add.setInnput(res.getString("input"));
               add.setOutput(res.getString("output"));
               add.setId(res.getString("id"));
               RtnRes.add(add);
               System.out.println("input:"+add.getInnput()+"|output:"+add.getOutput());
           }
           res.close();
       } catch (SQLException e) {
           throw new RuntimeException(e);
       }


       return RtnRes;
   }
   public Boolean ShutDownCon() {
       try {
           statement.close();
           con.close();
           return statement.isClosed() && con.isClosed();
       } catch (SQLException e) {
           throw new RuntimeException(e);
       }


   }
}
