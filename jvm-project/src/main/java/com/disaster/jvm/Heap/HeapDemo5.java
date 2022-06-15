package com.disaster.jvm.Heap;

import org.openjdk.jol.info.ClassLayout;

import java.lang.ref.PhantomReference;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class HeapDemo5 {
    byte[] bytes = new byte[new Random().nextInt(1024 * 200)];
//    SoftReference<byte[]> bytes1 = new SoftReference<>(new byte[new Random().nextInt(1024 * 200)]);
//    WeakReference<byte[]> bytes2 = new WeakReference<>(new byte[new Random().nextInt(1024 * 200)]);

    public static void main(String[] args) {
        List<HeapDemo5> list = new ArrayList<HeapDemo5>();
        HeapDemo5 test = new HeapDemo5();
        System.out.println(ClassLayout.parseInstance(test).toPrintable());
        while (true) {
            list.add(new HeapDemo5());
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
