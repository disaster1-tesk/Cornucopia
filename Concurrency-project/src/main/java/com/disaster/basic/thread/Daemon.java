package com.disaster.basic.thread;



/**
 *  子线程是后台线程，当main线程执行完毕之后则会停止程序，如果子线程是用户线程，当main线程执行完毕之后则会等待子线程执行完毕才会关闭程序
 *  这种实现是由jvm调用底层c所实现，同时java的jvm的线程模型是采用的一个内核线程对应一个用户线程
 */
public class Daemon {
    public static void main(String[] args) {
        daemon();
//        unDaemon();
    }

    private static void daemon() {
        Thread thread1 = new Thread(new ThreadGroup("group1"), () -> {
            while (true) {

            }
        }, "thread1", 100);
        thread1.setDaemon(true);
        thread1.start();
    }

    private static void unDaemon() {
        Thread thread1 = new Thread(new ThreadGroup("group1"), () -> {
            while (true) {

            }
        }, "thread1", 100);
        thread1.setDaemon(false);
        thread1.start();
    }
}
