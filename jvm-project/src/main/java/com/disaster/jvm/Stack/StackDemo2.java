package com.disaster.jvm.Stack;
/*
设置栈的大小：
    未设置之前：num :11289
    设置之后：-Xss256k  num :2302
 */
public class StackDemo2 {
    public static int num = 0;
    public static void main(String[] args) {
        System.out.println(num);
        num++;
        main(args);
    }
}
