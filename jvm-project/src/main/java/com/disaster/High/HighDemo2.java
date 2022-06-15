package com.disaster.High;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
/*
-XX:+HeapDumpOnOutOfMemoryError:在程序发生OOM时，导出应用程序的当前堆快照。
-XX:HeapDumpPath:可以指定堆快照的保存位置
例如：-Xmx100m -XX:+heapDumpOnOutOfMemoryError -XX:HeapDumpPath=D:\m.hprof
 */
public class HighDemo2 {
    public static void main(String[] args) {
        List<byte[]> lists = new ArrayList();
        for (int i = 0; i < 1000; i++) {
            byte[] bytes = new byte[1024*1024];
            lists.add(bytes);
            try {
                TimeUnit.MILLISECONDS.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
