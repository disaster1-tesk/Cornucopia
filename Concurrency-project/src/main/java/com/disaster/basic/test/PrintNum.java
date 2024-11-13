package com.disaster.basic.test;


import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

public class PrintNum {
    private int state = 0;

    public void add() {
        if (state == 0) {
            state++;
            System.out.println(Thread.currentThread().getName() + " -- >" + state);
        }

    }

    public void sub() {
        if (state == 1) {
            state--;
            System.out.println(Thread.currentThread().getName() + " -- >" + state);
        }
    }

    public static void main(String[] args) {
        semaphore();
    }


    private static void semaphore() {
        ReentrantLock lock1 = new ReentrantLock();
        Semaphore semaphore = new Semaphore(5);
        Semaphore semaphore1 = new Semaphore(5);
        PrintNum printNum = new PrintNum();
        for (int i = 0; i < 10; i++) {
            //加
            if ((i & 1) == 0) {
                Thread thread = new Thread(() -> {
                    while (true) {
                        lock1.lock();
                        if (printNum.state == 0) {
                            printNum.add();
                        }
                        lock1.unlock();
                        semaphore1.release();
                        try {
                            semaphore.acquire();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }, "thread" + i);
                thread.start();
            }
            //减
            if ((i & 1) == 1) {
                Thread thread = new Thread(() -> {
                    while (true) {
                        lock1.lock();
                        if (printNum.state == 1) {
                            printNum.sub();
                        }
                        lock1.unlock();
                        semaphore.release();
                        try {
                            semaphore1.acquire();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }, "thread" + i);
                thread.start();
            }
        }
    }


}
