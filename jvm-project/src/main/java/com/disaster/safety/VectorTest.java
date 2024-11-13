package com.disaster.safety;

import java.util.Vector;

public class VectorTest {
    private static Vector<Integer> vector = new Vector();

    public static void main(String[] args) {
        while (true) {
            for (int i = 0; i < 10; i++) {
                vector.add(i);
            }
            Thread removedThread = new Thread(() -> {
                synchronized (vector) {
                    for (int i = 0; i < vector.size(); i++) {
                        vector.remove(i);
                    }
                }
            });

            Thread printThread = new Thread(() -> {
                synchronized (vector) {
                    for (int i = 0; i < vector.size(); i++) {
                        System.out.println((vector.get(i)));
                    }
                }
            });
            removedThread.start();
            printThread.start();
            while (Thread.activeCount() > 30) ;
        }
    }
}
