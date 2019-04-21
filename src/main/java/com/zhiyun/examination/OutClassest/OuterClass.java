package com.zhiyun.examination.OutClassest;

/**
 * @Title: OuterClass
 * @ProjectName: examination
 * @Description: TODO
 * @author: jiangxing
 * @date 2019/4/2110:59
 */

public class OuterClass {

    private class InterClass {
        public InterClass() {
            System.err.println("InterClass Create");
        }
    }

    public OuterClass() {
        InterClass ic=new InterClass();
        System.err.println("create Out class");
    }

    public static void main(String[] args) {
        OuterClass oc=new OuterClass();

    }
}
