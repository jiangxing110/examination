package com.zhiyun.examination.ThreadTest;

/**
 * @Classname theFirstIdea
 * @Description :子线程循环10次，接着主线程循环100，接着又回到子线程循环10次
 * 接着再回到主线程又循环100，如此循环50次，请写出程序。
 * @Date 2019/4/22 8:46
 * @Created by jiangxing
 */
public class theFirstIdea {


    public static void main(String[] args) throws InterruptedException {
        MyService ms = new MyService();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 50; i++) {
                    ms.sub(i);
                }
            }
        }).start();
        Thread.sleep(1000);

        for (int i = 0; i < 50; i++) {
            ms.main(i);
        }
    }


    static class MyService {
        boolean beShouldSub = true;//是否执行子线程标志位

        public synchronized void sub(int i) {
            if (!beShouldSub){
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            for (int j = 1; j <= 100; j++) {
                System.out.println("sub thread sequeue of " + j + "loop of " + i);
            }
            beShouldSub = false;//子线程执行完毕，子线程标志位设为false
            this.notify();//唤醒主线程
        }

        public synchronized void main(int i) {
            if(beShouldSub){
                try {
                    this.wait();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            for (int j = 1; j <= 100; j++) {
                System.out.println("Main thread sequeue of " + j + "loop of " + i);
            }

            beShouldSub = true;//子线程执行完毕，子线程标志位设为false
            this.notify();//唤醒主线程
        }
    }
}
