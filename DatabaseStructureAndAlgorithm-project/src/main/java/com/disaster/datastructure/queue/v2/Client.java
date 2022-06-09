package com.disaster.datastructure.queue.v2;

public class Client {
    public static void main(String[] args) {
        QueueImp queueImp = new QueueImp();
        queueImp.push("你好");
        queueImp.push("世界");
        queueImp.push(10);
        queueImp.push(232);
        queueImp.push('s');
        queueImp.push(true);
        System.out.println(queueImp.size());
        System.out.println(queueImp.pop());
        System.out.println(queueImp.pop());
        System.out.println(queueImp.pop());
        System.out.println(queueImp.pop());
        System.out.println(queueImp.pop());
        System.out.println(queueImp.pop());
    }
}
