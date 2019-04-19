package com.zhiyun.examination.IoTest;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * @Title: FileFiterOperation
 * @ProjectName: examination
 * @Description: 实现从文件中一次读出一个字符的操作；
 * @author: jiangxing
 * @date 2019/4/1813:46
 */

public class FileFiterOperation {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(
                "D:\\java\\Tank.java"));
        int ch;
        while ((ch = reader.read()) > 0) {
            System.out.println((char) ch);
        }
    }
}
