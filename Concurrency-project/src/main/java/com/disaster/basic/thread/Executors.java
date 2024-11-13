package com.disaster.basic.thread;

import java.util.concurrent.FutureTask;
import java.util.concurrent.atomic.AtomicInteger;

public class Executors {
    public static void main(String[] args) throws InterruptedException {
        AtomicInteger integer = new AtomicInteger(0);
        Consumer consumer = new Consumer(integer);
        Producer producer = new Producer(integer);
        FutureTask<Object> futureTask = new FutureTask<>(producer);
        FutureTask<Object> futureTask1 = new FutureTask<>(consumer);
        Thread thread = new Thread(futureTask);
        Thread thread1 = new Thread(futureTask1);
        thread.start();
        thread1.start();
        thread1.interrupt();
    }
}
