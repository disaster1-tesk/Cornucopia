package com.disaster.basic.thread;


public class ThreadLocal {
    private static java.lang.ThreadLocal local = new java.lang.ThreadLocal();
    private static InheritableThreadLocal inheritableLocal = new InheritableThreadLocal();

    public static void main(String[] args) {
//        threadLockSet();
//        mainInThreadLocal();
        mainInInheritableThreadLocal();
    }

    private static void mainInThreadLocal() {
        local.set(Thread.currentThread().getName() + " local variable");
        Thread thread3 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "  " + local.get());
        }, "thread3");
        thread3.start();
    }

    private static void mainInInheritableThreadLocal() {
        inheritableLocal.set(Thread.currentThread().getName() + " local variable");
        Thread thread3 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "  " + inheritableLocal.get());
            inheritableLocal.set(Thread.currentThread().getName() + " local variable");
        }, "thread4");
        System.out.println(Thread.currentThread().getName() + "  " + inheritableLocal.get());
        thread3.start();
    }

    private static void threadLockSet() {
        Thread thread1 = new Thread(() -> {
            local.set(Thread.currentThread().getName() + " local variable");
            System.out.println(local.get());
            local.remove();
            System.out.println(Thread.currentThread().getName() + " remove after: " + local.get());
        }, "thread1");

        Thread thread2 = new Thread(() -> {
            local.set(Thread.currentThread().getName() + " local variable");
            System.out.println(local.get());
            local.remove();
            System.out.println(Thread.currentThread().getName() + " remove after: " + local.get());
        }, "thread2");

        thread1.start();
        thread2.start();
    }
}
