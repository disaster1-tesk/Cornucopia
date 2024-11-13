package com.disaster.basic.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicInteger;

public class Consumer implements Callable<Object> {
    private AtomicInteger queue;

    public Consumer(AtomicInteger queue) {
        this.queue = queue;
    }

    @Override
    public Object call() throws Exception {
        synchronized (queue) {
            while (true) {
                if (queue.get() == 0) {
                    //挂起当前线程
                    System.out.println(queue);
                    queue.wait();
                    //唤醒其他线程
                    queue.notifyAll();
                    return null;
                } else {
                    queue.set(queue.get() - 1);
                    System.out.println(queue);
                }

            }
        }

    }
}
