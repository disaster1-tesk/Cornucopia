package com.disaster.jvm.Heap;

public class HeapDemo2 {
    public static void main(String[] args) {
        System.out.println("HeapDemo2....begin");
        try {
            Thread.sleep(1000000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("HeapDemo2....end");
    }
}
