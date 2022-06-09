package com.disaster.example.stackforqueue.v1;

import java.util.Stack;

/**
 * @ClassName Client
 * @Description TODO
 * @Author Disaster
 * @Date 2021/7/13 9:17
 * @Version 1.0
 **/
public class Client {
    public static void main(String[] args) {

    }
}

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
        if (out.isEmpty()) {
            return -1;
        } else {
            int deleteItem = out.pop();
            return deleteItem;
        }
    }

}
