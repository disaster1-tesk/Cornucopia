package com.disaster.basic.thread;


/**
 * · 互斥条件: 指线程对己经获取到的资源进行排它性使用 ， 即该资源同时只由一个线程占用。如果此时 还有其他 线程请求获取该资源 ，则请求者只能等待，直至占有资源的线程释放该资源。
 * · 请求并持有条件 : 指一个线程己经持有了至少一个资源， 但又提出了新的资源请求， 而新资源己被其他线程占有，所以当前线程会被阻塞 ，但阻塞的同时 并不释放自己己经获取的资源。
 * · 不可剥夺条件 : 指线程获取到的资源在自己使用完之前不能被其他线程抢占 ， 只有在自己使用完毕后才由自己释放该资源。
 * · 环路等待条件 : 指在发生死锁时 ，必然存在一个线程→资源的环形链 ， 即线程集合 {TO,T1,T2，...，Tn}中的TO正在等待一个T1占用的资源， T1正在等待T2占用的资源，......Tn正在等待己被 TO 占用的资源。
 */
public class DeadLock {
    private static Object resource1 = new Object();
    private static Object resource2 = new Object();

    public static void main(String[] args) {
        deadLock();
    }

    private static void deadLock() {
        Thread thread1 = new Thread(() -> {
            synchronized (resource1){
                System.out.println(Thread.currentThread().getName() + "  obtain resource1");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "  try obtain resource2");
                synchronized (resource2){

                }
            }
        }, "thread1");
        Thread thread2 = new Thread(() -> {
            synchronized (resource2){
                System.out.println(Thread.currentThread().getName() + "  obtain resource2");
                System.out.println(Thread.currentThread().getName() + "  try obtain resource1");
                synchronized (resource1){

                }
            }
        }, "thread2");
        thread1.start();
        thread2.start();
    }
}
