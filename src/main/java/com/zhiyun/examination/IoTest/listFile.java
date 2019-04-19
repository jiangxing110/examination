package com.zhiyun.examination.IoTest;/**
 * @Title: listFile
 * @ProjectName: examination
 * @Description: TODO
 * @author: jiangxing
 * @date 2019/4/1813:29
 */

import java.io.File;

/**
 * @Classname listFile
 * @Description TODO
 * @Date 2019/4/18 13:29
 * @Created by jiangxing
 */
public class listFile {

    public static void main(String[] args) {
       File file= new File("E:\\");
        for (File listFile : file.listFiles()) {
            if (listFile.isDirectory()){
                System.err.println(listFile.toString());

            }
        }
    }
}
