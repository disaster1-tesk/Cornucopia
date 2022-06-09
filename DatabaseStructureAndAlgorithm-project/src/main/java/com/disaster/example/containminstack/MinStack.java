package com.disaster.example.containminstack;

import java.util.LinkedList;
import java.util.Stack;

class MinStack {
    Stack<Integer> A;
    LinkedList<Integer> B;//最好用两个Stack来实现，内存占用比较少！
    public MinStack() {
        A = new Stack<>();
        B = new LinkedList<>();
    }
    public void push(int x) {
        A.add(x);
        if(B.isEmpty() || B.getFirst() >= x)
            B.addFirst(x);
    }
    public void pop() {
        if(A.pop().equals(B.getFirst()))
            B.removeFirst();
    }
    public int top() {
        return A.peek();
    }
    public int min() {
        return B.getFirst();
    }

}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(x);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.min();
 */
