package com.disaster.jvm.Heap;

/*
-Xms60m -Xmx60m -XX:NewRatio=2 -XX:SurvivorRatio=8 -XX:+PrintGCDetails
 */
public class HeapDemo6 {
    public static void main(String[] args) {
        byte[] bytes = new byte[1024*1024*20];
    }
}
