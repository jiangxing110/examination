package com.zhiyun.examination.ThreadTest;/**
 * @Title: MyThread
 * @ProjectName: examination
 * @Description: TODO
 * @author: jiangxing
 * @date 2019/4/1814:49
 */

/**
 * @Classname MyThread
 * @Description TODO
 * @Date 2019/4/18 14:49
 * @Created by jiangxing
 */
public class MyThread {

    private int j;

    public static void main(String[] args) {
        MyThread myThread=new MyThread();
        Inc inc=myThread.new Inc();
        Dec dec=myThread.new Dec();
        for (int i = 0; i < 2; i++) {
            Thread t = new Thread(inc);
            t.start();
            t = new Thread(dec);
            t.start();

        }
    }

    private synchronized void inc() {
        j++;
        System.out.println(Thread.currentThread().getName() + "-inc:" + j);
    }

    private synchronized void dec() {
        j--;
        System.out.println(Thread.currentThread().getName() + "-dec:" + j);
    }

    class Inc implements Runnable {

        @Override
        public void run() {
            for (int i = 0; i < 20; i++) {
                inc();
            }
        }
    }

    class Dec implements Runnable {

        @Override
        public void run() {
            for (int i = 0; i < 20; i++) {
                dec();
            }
        }
    }
}
