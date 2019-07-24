package com.zhiyun.examination.ThreadTest;/**
 * @Title: singleThreadExecutorTest
 * @ProjectName: examination
 * @Description: TODO
 * @author: jiangxing
 * @date 2019/7/1818:16
 */

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Classname singleThreadExecutorTest
 * @Description TODO
 * @Date 2019/7/18 18:16
 * @Created by jiangxing
 */
public class singleThreadExecutorTest {
    public static void main(String[] args) {
        // TODO Auto-generated method stub

        //创建一个可重用固定线程数的线程池
        ExecutorService pool= Executors.newSingleThreadExecutor();

        //创建实现了Runnable接口对象，Thread对象当然也实现了Runnable接口;


        MyThreadRun t1=new MyThreadRun();

        MyThreadRun t2=new MyThreadRun();

        MyThreadRun t3=new MyThreadRun();

        MyThreadRun t4=new MyThreadRun();

        MyThreadRun t5=new MyThreadRun();

        //将线程放到池中执行；

        pool.execute(t1);

        pool.execute(t2);

        pool.execute(t3);

        pool.execute(t4);

        pool.execute(t5);

        //关闭线程池

        pool.shutdown();



    }
}
