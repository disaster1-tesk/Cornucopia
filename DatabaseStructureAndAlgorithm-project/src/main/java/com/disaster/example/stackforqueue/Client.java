package com.disaster.example.stackforqueue;

import java.util.Stack;

public class Client {
    public static void main(String[] args) {
        CQueue cQueue = new CQueue();
        System.out.println(cQueue.deleteHead());
        cQueue.appendTail(5);
        cQueue.appendTail(2);
        System.out.println(cQueue.deleteHead());
        System.out.println(cQueue.deleteHead());
        System.out.println(cQueue.deleteHead());
        System.out.println("=====================================");
        System.out.println(cQueue.deleteHead1());
        System.out.println(cQueue.deleteHead1());
        System.out.println(cQueue.deleteHead1());
        cQueue.appendTail(5);
        cQueue.appendTail(2);
        System.out.println(cQueue.deleteHead1());
        System.out.println(cQueue.deleteHead1());
        System.out.println(cQueue.deleteHead1());
    }
}
//  用两个栈实现一个队列。队列的声明如下，请实现它的两个函数 appendTail 和 deleteHead ，
//  分别完成在队列尾部插入整数和在队列头部删除整数的功能。(若队列中没有元素，deleteHead 操作返回 -1 )

class CQueue {
    private Stack<Integer> in = null;
    private Stack<Integer> out = null;

    public CQueue() {
        in = new Stack();
        out = new Stack();
    }

    public void appendTail(int value) {
        in.push(value);
    }

    public int deleteHead() {
        if (out.isEmpty()) {
            while (!in.isEmpty()) {
                out.push(in.pop());
            }
        }
        if (out.isEmpty()){
            return -1;
        }else{
            return out.pop();
        }
    }
    public int deleteHead1(){
        while(!in.isEmpty()){
            out.push(in.pop());
        }
        if (out.isEmpty()){
            return -1;
        }else{
            return out.pop();
        }
    }
}
