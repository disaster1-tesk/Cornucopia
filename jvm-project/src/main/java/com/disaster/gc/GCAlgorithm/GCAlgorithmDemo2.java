package com.disaster.gc.GCAlgorithm;

import java.util.Scanner;

public class GCAlgorithmDemo2 {
    static GCAlgorithmDemo2 test1 = new GCAlgorithmDemo2();
    static GCAlgorithmDemo2 test2 = new GCAlgorithmDemo2();

    public static void main(String[] args) {
        System.out.println("是否清除对象？");
        new Scanner(System.in).next();
        test1 = null;
        test2 = null;
        System.gc();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (test1 == null && test2 == null) {
            System.out.println("内存清除完毕,类并没有重写finalize方法，直接进入不可触及状态");
        } else {
            System.out.println("重写了finalize方法，进入可重生状态");
        }
        System.out.println("是否再次清除对象？");
        new Scanner(System.in).next();
        test1 = null;
        test2 = null;
        System.gc();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (test1 == null && test2 == null) {
            System.out.println("内存清除完毕");
        }
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("调用finalize方法");
        test1 = this;
        test2 = this;
    }
}
