package com.disaster.synchronize;

public class Demo1 {
    public static void main(String[] args) {
        synchronized (Demo1.class){
            System.out.println("我被锁住了ing...");
        }
    }
}
