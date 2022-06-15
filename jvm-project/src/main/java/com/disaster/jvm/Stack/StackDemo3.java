package com.disaster.jvm.Stack;

import org.openjdk.jol.info.ClassLayout;

public class StackDemo3 {
    public StackDemo3() {
    }

    public static void main(String[] args) {
        int num = 0;
        double d = 2.0;
        System.out.println(num);
        StackDemo3 test = new StackDemo3();
        System.out.println(ClassLayout.parseInstance(test).toPrintable());
    }

    public void test1() {
        int a = 1;
        double b = 3.0;
        char c = 'a';
        Long l = 1l;
        int d = 1;
        System.out.println("test1...begin");
        test2();
        System.out.println("test1...end");
    }

    public void test2() {
        System.out.println("test2...begin");
        test3();
        System.out.println("test2...end");
    }

    public int test3() {
        System.out.println("test3...begin");
        System.out.println("test3...end");
        return 1;
    }
}
