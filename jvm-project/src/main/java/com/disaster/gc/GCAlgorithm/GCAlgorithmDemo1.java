package com.disaster.gc.GCAlgorithm;
/*
引用计数算法
下方的例子就是为了证明hotspot不是使用的引用计数算法来进行垃圾判别
 */
public class GCAlgorithmDemo1 {
    public static void main(String[] args) {
        test test1 = new test();
        test test2 = new test();
        test1.t = test2;
        test2.t = test1;
        test1=null;
        test2= null;
        System.gc();
    }
}
class test{
    public byte[] bytes = new byte[1024*1024*10];
    public  test t;
}
