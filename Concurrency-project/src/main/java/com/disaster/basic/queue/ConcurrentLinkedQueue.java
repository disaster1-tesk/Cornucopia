package com.disaster.basic.queue;

/**
 * ConcurrentLinkedQueue 是线程安全的无界非阻塞队列，其底层数据结构使用单向链表实现，对于入队和出队操作使用 CAS 来实现线程安全 。
 */
public class ConcurrentLinkedQueue {
    public static void main(String[] args) {
        java.util.concurrent.ConcurrentLinkedQueue<Object> concurrentLinkedQueue = new java.util.concurrent.ConcurrentLinkedQueue<>();
        //offer操作是在队列末尾添加一个元素，如果传递的参数是 null 则抛出 NPE 异常，
        // 否则由于 ConcurrentLinkedQueue 是无界队列，该方法一直会返回 true。 另外，由于使用 CAS 无阻塞算法，因此该方法不会阻塞挂起调用线程
        concurrentLinkedQueue.offer("hello");
        //poll 方法在移除一个元素时，只是简单地使用 CAS 操作把当前节点的 item 值 设置为 null，然后通过重新设置头节点将该元素从队列里面移除，被移除的节点就成了孤立节点，这个节点会在垃圾回收时被回收掉。
        // 另外，如果在执行分支中发现头节点被修改了，要跳到外层循环重新获取新的头节点。
        concurrentLinkedQueue.poll();
        //peek 操作的代码与 poll 操作类似，只是前者只获取队列头元素但是并不从队列里将它删除，而后者获取后需要从队列里面将它删除。
        // 另外，在第一次调用 peek操作时，会删除哨兵节点，并让队列的 head 节点指向队列里面第一个元素或者 null。
        concurrentLinkedQueue.peek();
        //判断队列里面是否含有指定对象， 由于是遍历整个队列，所以像 size 操作一样结果也不是那么精确 ，有可能调用该方法时元素还在队列里面，但是遍历过程中其他线程才把该元素删除了，那么就会返回 false
        concurrentLinkedQueue.contains("hello");
        //如果队列里面存在该元素则删除该元素，如果存在多个则删除第一个，并返回 true, 否则返回 false
        concurrentLinkedQueue.remove("hello");
    }
}
