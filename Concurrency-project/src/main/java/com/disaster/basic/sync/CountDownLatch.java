package com.disaster.basic.sync;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

public class CountDownLatch {
    public static void main(String[] args) {
        await();
//        awaitByTime();
    }

    private static void awaitByTime() {
        java.util.concurrent.CountDownLatch countDownLatch = new java.util.concurrent.CountDownLatch(2);
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.submit(()->{
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }
            countDownLatch.countDown();
            System.out.println(Thread.currentThread().getName() + " is countDowned");
        });
        executorService.submit(()->{
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
            }
            countDownLatch.countDown();
            System.out.println(Thread.currentThread().getName() + " is countDowned");
        });
        try {
            countDownLatch.await(1, TimeUnit.SECONDS);
            System.out.println("main thread is down");
            executorService.shutdownNow();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void await() {
        java.util.concurrent.CountDownLatch countDownLatch = new java.util.concurrent.CountDownLatch(2);
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.submit(()->{
            System.out.println(Thread.currentThread().getName() + " begin");
            LockSupport.park();
            countDownLatch.countDown();
            System.out.println(Thread.currentThread().getName() + " is countDowned");
        });
        executorService.submit(()->{
            System.out.println(Thread.currentThread().getName() + " begin");
            LockSupport.park();
            countDownLatch.countDown();
            System.out.println(Thread.currentThread().getName() + " is countDowned");
        });
        try {
            executorService.shutdownNow();
            countDownLatch.await();
            System.out.println("main thread is down");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
