package com.zhiyun.examination.StringTest;



/**
 * @Title: SplitString
 * @ProjectName: examination
 * @Description: 编写一个截取字符串的函数，输入为一个字符串和字节数，输出为按字节截取的字符串。
 * 但是要保证汉字不被截半个，如“我ABC”4，应该截为“我AB”，输入“我ABC汉DEF”，6，应该输出为“我ABC”而不是“我ABC+汉的半个”
 * @author: jiangxing
 * @date 2019/4/1813:53
 */

public class SplitString {

    public static void main(String[] args) throws Exception {
        String string = "abc你是谁";
        System.out.println(splitString(string, 4));
    }

    public static String splitString(String str, int length)
            throws Exception {
       if (str==null||str.length()<1||length<1){
           return "";
       }
       int worldCount=0;
       byte[] bytes=str.getBytes("GBK");

       for (int i=0;i<length;i++){
           if (bytes[i]<0){
               worldCount++;
           }
       }
       if (worldCount%2==0){
           return str.substring(0,(length-worldCount/2));
       }else {
           return str.substring(0,(length-worldCount/2-1));
       }
    }

}
