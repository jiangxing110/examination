package com.zhiyun.examination.IoTest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * @Title: DSOOperation
 * @ProjectName: examination
 * @Description: TODO
 * @author: jiangxing
 * @date 2019/4/1813:38
 */

public class DSOOperation {

    public static void main(String[] args) {
        Runtime run=Runtime.getRuntime();
        try {
            //cmd /c 下面这句就是删除文件
            run.exec("cmd /c  del E:\\logs\\log");

            //控制台显示信息
            Process p=run.exec("cmd /c  dir src");
            InputStream is=p.getInputStream();
            BufferedReader read=new BufferedReader(new InputStreamReader(is));
            while(true){
                String s=read.readLine();
                if(s==null){break;}
                System.err.println(s);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
