package com.disaster.basic.thread;

import lombok.SneakyThrows;

public class Notifies {
    public static void main(String[] args) {
//        notifyTest();
        notifyAllTest();
    }

    @SneakyThrows
    public static void notifyTest() {
        Object o = new Object();
        Thread thread0 = new Thread(() -> {
            synchronized (o) {
                try {
                    System.out.println("thread1 is wait");
                    o.wait();
                    System.out.println("thread1 is open");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        Thread thread1 = new Thread(() -> {
            synchronized (o) {
                try {
                    System.out.println("thread2 is wait");
                    o.wait();
                    System.out.println("thread2 is open");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        Thread thread2 = new Thread(() -> {
            synchronized (o) {
                o.notify();
                System.out.println("thread3 is notify");
            }
        });
        thread0.start();
        thread1.start();
        Thread.sleep(200);
        thread2.start();
    }

    @SneakyThrows
    public static void notifyAllTest() {
        Object o = new Object();
        Thread thread0 = new Thread(() -> {
            synchronized (o) {
                try {
                    System.out.println("thread1 is wait");
                    o.wait();
                    System.out.println("thread1 is open");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        Thread thread1 = new Thread(() -> {
            synchronized (o) {
                try {
                    System.out.println("thread2 is wait");
                    o.wait();
                    System.out.println("thread2 is open");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        Thread thread2 = new Thread(() -> {
            synchronized (o) {
                o.notifyAll();
                System.out.println("thread3 is notify");
            }
        });
        thread0.start();
        thread1.start();
        Thread.sleep(200);
        thread2.start();
    }


}
