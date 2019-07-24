package com.zhiyun.examination.ThreadTest;/**
 * @Title: MyThreadRun
 * @ProjectName: examination
 * @Description: TODO
 * @author: jiangxing
 * @date 2019/7/1818:15
 */

/**
 * @Classname MyThreadRun
 * @Description TODO
 * @Date 2019/7/18 18:15
 * @Created by jiangxing
 */
public class MyThreadRun extends Thread {

    @Override
    public void run() {

        System.out.println(Thread.currentThread().getName() + "正在执行....");
    }
}