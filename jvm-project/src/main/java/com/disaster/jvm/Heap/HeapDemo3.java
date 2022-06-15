package com.disaster.jvm.Heap;

public class HeapDemo3 {
    public static void main(String[] args) {
        long l = Runtime.getRuntime().totalMemory()/1024/1024;
        long l1 = Runtime.getRuntime().maxMemory()/1024/1024;
        System.out.println("-Xms" + l+"M");
        System.out.println("-Xmx" + l1+"M");
        long s = (l*64/1024);
        long m = (l1*4/1024);
        System.out.println("电脑最大内存"+s+"G");
        System.out.println("电脑最大能使用的内存"+m+"G");
        try {
            Thread.sleep(1000000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
