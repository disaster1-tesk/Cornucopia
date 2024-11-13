package com.disaster.basic.sync;

import lombok.SneakyThrows;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Semaphore {
    @SneakyThrows
    public static void main(String[] args) {
        semaphore();
    }

    private static void semaphore() throws InterruptedException {
        java.util.concurrent.Semaphore semaphore = new java.util.concurrent.Semaphore(0);
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.submit(()->{
            System.out.println(Thread.currentThread().getName()+" is over");
            semaphore.release();
        });
        executorService.submit(()->{
            System.out.println(Thread.currentThread().getName() + " is over");
            semaphore.release();
        });
        Thread.sleep(1000);
        semaphore.acquire(2);
        System.out.println("all child thread is over");
        executorService.shutdown();
    }
}
