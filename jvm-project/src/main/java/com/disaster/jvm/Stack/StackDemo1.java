package com.disaster.jvm.Stack;
/*
常见的与栈有关的几种异常：
    1.OutOfMemoryError：栈是可以动态分配内存和固定内存的，当我们是动态分配栈的内存空间时一旦线程所开辟的空间超过虚拟机所能分配的最大内存空间之后就会出现OOM异常
    2.StackOverflowError:栈是可以动态分配内存和固定内存的，当我们固定分配栈的内存空间时一旦线程所开辟的栈空间大于分配的内存大小，那么就会出现这个异常
 */
public class StackDemo1 {
    public static void main(String[] args) {
//        main(args);//Exception in thread "main" java.lang.StackOverflowError
        for (int i = 0; i < 1000000; i++) {
            new Thread(new myRunnable()).start(); //Exception in thread "main" java.lang.OutOfMemoryError
        }

    }
}
class myRunnable implements Runnable{
    @Override
    public void run() {
        for (int i = 0; i < 1000000; i++) {
            int sum =i;
        }
    }
}