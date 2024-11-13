package com.disaster.basic.thread;

import lombok.SneakyThrows;

public class Volatile {
    private static int a = 0;
    private static boolean ready = false;

    public static void main(String[] args) {
        _volatile();
    }

    @SneakyThrows
    private static void _volatile() {
        Thread thread1 = new Thread(() -> {
            while (ready) {
                System.out.println(Thread.currentThread().getName() + " a = " + a);
                break;
            }
        }, "thread1");
        Thread thread2 = new Thread(() -> {
            a = 2;
            ready = true;
        }, "thread2");
        thread1.start();
        thread2.start();
        Thread.sleep(1000);
    }
}
