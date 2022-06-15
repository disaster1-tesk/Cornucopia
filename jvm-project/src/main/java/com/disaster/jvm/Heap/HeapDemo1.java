package com.disaster.jvm.Heap;


/**
 * -Xms10m -Xmx10m
 * 堆分配指令：-Xms与-Xmx
 *  -Xms 用来设置堆空间（新生代+老年代）的初始内存
 *          -X 是JVM的运行参数
 *          ms 是memory start
 *  -Xmx 用来设置堆空间（新生代+老年代）的最大内存
 *
 * 默认堆空间大小：
 *      初始内存大小：物理内存大小/64
 *      最大内存大小：物理电脑内存大小/4
 *
 *-XX:+PrintGCDetails 打印堆中各内存空间的基本信息
 */
public class HeapDemo1 {
    public static void main(String[] args) {
        System.out.println("HeapDemo1....begin");
        try {
            Thread.sleep(1000000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("HeapDemo1....end");
    }
}
