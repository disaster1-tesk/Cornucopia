package com.disaster.jvm;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Test {
    public static void main(String[] args) {
        List<byte[]> list = new ArrayList();
        for (int i = 0; i < 1000; i++) {
            byte[] bytes = new byte[1024*1024];
            list.add(bytes);
           /* try {
                TimeUnit.MILLISECONDS.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }*/
        }
    }
}
