package com.disaster.basic.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicInteger;

public class Producer implements Callable<Object> {
    private AtomicInteger queue;

    public Producer(AtomicInteger queue) {
        this.queue = queue;
    }

    @Override
    public Object call() throws Exception {
        synchronized (queue) {
            while (true) {
                if (queue.get() == 10086) {
                    System.out.println(queue);
                    //挂起当前线程
                    queue.wait();
                    //唤醒其他线程
                    queue.notifyAll();
                    return queue;
                } else {
                    queue.set(queue.get() + 1);
                    Thread.sleep(1000);
                    System.out.println(queue);
                }
            }
        }
    }
}
