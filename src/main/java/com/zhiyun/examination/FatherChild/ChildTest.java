package com.zhiyun.examination.FatherChild;/**
 * @Title: ChildTest
 * @ProjectName: examination
 * @Description: TODO
 * @author: jiangxing
 * @date 2019/4/1813:04
 */


public class ChildTest extends FatherTest {
    public ChildTest() {
        System.err.println("创建子类");
    }


    public static void main(String[] args) {
        FatherTest fatherTest=new FatherTest();
        ChildTest childTest=new ChildTest();
    }
}
