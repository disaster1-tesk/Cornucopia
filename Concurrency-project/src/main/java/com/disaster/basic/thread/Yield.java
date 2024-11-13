package com.disaster.basic.thread;

/**
 * sleep 与 yield 方法的区别在于，当线程调用 sleep 方法时调用线程会被阻塞挂起指定的时间，在这期间线程调度器不会去调度该线程 。
 * 而调用 yield 方法时，线程只是让出自己剩余的时间片，并没有被阻塞挂起，而是处于就绪状态，线程调度器下一次调度 时就有可能调度到当前线程执行 。
 */
public class Yield {
    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            System.out.println("threadA is running");
        });
        Thread thread1 = new Thread(() -> {
            System.out.println("threadB is running");
            System.out.println("threadB is stop");
        });
        Thread.yield();
        thread.start();
        thread1.start();
    }
}
