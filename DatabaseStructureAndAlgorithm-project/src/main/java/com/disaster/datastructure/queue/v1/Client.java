package com.disaster.datastructure.queue.v1;


import java.util.LinkedList;

/**
 * 队列（Queue）：先进先出
 * 能实现栈的基本都能实现队列
 * @author Lenovo
 */
public class Client {
    public static void main(String[] args) {

    }
    public void funV1(){
        LinkedList<Integer> queue = new LinkedList<>();
        queue.add(10);
        queue.add(20);
        queue.removeFirst();
        System.out.println(queue.contains(20));
    }
}
