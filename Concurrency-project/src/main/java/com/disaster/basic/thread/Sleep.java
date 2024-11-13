package com.disaster.basic.thread;

import lombok.SneakyThrows;

public class Sleep {
    private static volatile Object o = new Object();

    @SneakyThrows
    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            synchronized (o) {
                try {
                    System.out.println(Thread.currentThread().getName() + "   obtain lock");
                    //调用thread中的静态方法sleep会挂起阻塞线程，并不会释放锁资源，
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(Thread.currentThread().getName() + "free lock");
        }, "thread1");

        Thread thread2 = new Thread(() -> {
            synchronized (o) {
                System.out.println(Thread.currentThread().getName() + "obtain lock");
            }
            System.out.println(Thread.currentThread().getName() + "free lock");
        }, "thread2");
        thread.start();
        thread2.start();
        thread2.join();

    }



}
