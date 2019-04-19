package com.zhiyun.examination.Singleton;/**
 * @Title: Singleton
 * @ProjectName: examination
 * @Description: TODO
 * @author: jiangxing
 * @date 2019/4/1815:18
 */


public class Singleton {

    /*private static Singleton singleton=null;

    public Singleton() {
    }

    public static synchronized Singleton getSingleton() {
        if (singleton==null){
            singleton=new Singleton();
        }
        return singleton;
    }*/
private static Singleton singleton= new Singleton();

    public Singleton() {
    }

    public static Singleton getSingleton() {
        return singleton;
    }
}
