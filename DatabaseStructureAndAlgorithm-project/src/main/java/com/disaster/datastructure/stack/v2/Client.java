package com.disaster.datastructure.stack.v2;

public class Client {
    public static void main(String[] args) {
        StackImp stackImp = new StackImp();
        stackImp.push("你好");
        stackImp.push("世界");
        stackImp.push(10);
        stackImp.push(232);
        stackImp.push('s');
        stackImp.push(true);
        System.out.println(stackImp.size());
        System.out.println(stackImp.pop());
        System.out.println(stackImp.pop());
        System.out.println(stackImp.pop());
        System.out.println(stackImp.pop());
        System.out.println(stackImp.pop());
        System.out.println(stackImp.pop());
    }
}
