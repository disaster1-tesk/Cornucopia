package com.disaster.datastructure.stack.v1;


import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Stack;

/*
栈（stack）：先进后出
能够实现栈的有LinkedList、tack(S不推荐使用这个实现，因为效率较低，它继承vector，而vector是synchronized实现的线程安全类）、ArrayDeque、数组（）
 */
public class Client {
    public static void main(String[] args) {

    }

    public void funV1() {
        LinkedList<Integer> stack = new LinkedList<>();
        stack.add(10);
        stack.add(20);
        stack.removeLast();
        System.out.println(stack.contains(10));
    }

    public void funV2() {
        Stack<Integer> stack = new Stack<>();
        stack.push(10);
        stack.push(20);
        stack.pop();
        System.out.println(stack.search(10));
    }

    public void funV3() {
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        stack.add(20);
        stack.removeLast();
        System.out.println(stack.contains(10));
    }

}
