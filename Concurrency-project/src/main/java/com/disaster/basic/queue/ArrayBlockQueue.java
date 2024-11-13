package com.disaster.basic.queue;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * 有界数组方式实现的阻塞队列
 */
public class ArrayBlockQueue {
    public static void main(String[] args) throws InterruptedException {
        ArrayBlockingQueue<Object> arrayBlockingQueue = new ArrayBlockingQueue(16);
        //向队列尾部插入一个元素，如果队列有空闲则插入后直接返回 true，如果队列己满则阻塞当前线程直到队列有空闲井插入成功后返回 true，如果在阻塞时被其他线程设置了中断标志，
        //则被阻塞线程会抛出 InterruptedException 异常而返回。另外，如果 e 元素为null则抛出NullPointerException 异常
        arrayBlockingQueue.put("test");
        //使用offer方法时，当队列满了就会丢弃要入队的元素，之后offer方法会返回 false，而不会阻塞当前线程
        arrayBlockingQueue.offer("test");
        //获取当前队列头部元素并从队列里面移除它。如果队列为空则阻塞当前线程直到队列 不为空然后返回元素，
        // 如果在阻塞时被其他线程设置了中断标志，则被阻塞线程会抛出 InterruptedException 异常而返回。
        arrayBlockingQueue.take();
        //从队列头部获取并移除一个元素，如果队列为空则返回 null,该方法是不阻塞的
        arrayBlockingQueue.poll();
        //获取队列头部元素但是不从队列里面移除它，如果队列为空则返回 null，该方法是不阻塞的
        arrayBlockingQueue.peek();
    }
}


