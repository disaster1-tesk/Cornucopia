package com.disaster.basic.queue;

import lombok.SneakyThrows;

/**
 * 独占锁实现的阻塞有界队列 LinkedBlockingQueue
 */
public class LinkedBlockingQueue {

    @SneakyThrows
    public static void main(String[] args) {
        java.util.concurrent.LinkedBlockingQueue<Object> linkedBlockingQueue = new java.util.concurrent.LinkedBlockingQueue<>();
        //向队列尾部插入一个元素，如果队列中有空闲则插入成功后返回 true，如果队列己满 则丢弃当前元素然后返回 false
        linkedBlockingQueue.offer("hello");
        //向队列尾部插入一个元素，如果队列中有空闲则插入后直接返回，如果队列己满则阻塞当前线程，直到队列有空闲插入成功后返回
        linkedBlockingQueue.put("world");
        //队列头部获取并移除一个元素，如果队列为空则返回 null，该方法是不阻塞的
        linkedBlockingQueue.poll();
        //获取队列头部元素但是不从队列里面移除它，如果队列为空则返回null。该方法是不会阻塞的
        linkedBlockingQueue.peek();
        //获取当前队列头部元素并从队列里面移除它。如果队列为空则阻塞当前线程直到队列
        //不为空然后返回元素 ，如果在阻塞时被其他线程设置了中断标志 ，InterruptedException异常而返回
        linkedBlockingQueue.take();
        //删除队列里面指定的元素，有则删除并返回 true，没有则返回 false
        linkedBlockingQueue.remove("hello");
    }
}
