package com.disaster.jvm.ClassAllocation;

import java.nio.ByteBuffer;
import java.util.Scanner;

public class ClassAllocationDemo1 {
    public static int BUFFER = 1024*1024*1024;
    public static void main(String[] args) {
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(BUFFER);
        System.out.println("直接内存分配完毕");
        Scanner scanner = new Scanner(System.in);
        scanner.next();

        byteBuffer = null;
        System.out.println("直接内存释放完毕");
        System.gc();
        scanner.next();
    }
}
