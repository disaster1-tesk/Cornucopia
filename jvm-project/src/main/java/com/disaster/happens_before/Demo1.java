package com.disaster.happens_before;

import java.time.Instant;
import java.util.concurrent.CountDownLatch;

public class Demo1 {
    private static Integer i = null;
    private static Integer k = null;

    public static void main(String[] args) throws InterruptedException {
        example1();
//        example2();
    }

    public static void example1() {
        i = 1;
        CountDownLatch latch = new CountDownLatch(0);
        B worker1 = new B("B", latch);
        worker1.run();
    }

    public static void example2() throws InterruptedException {
        i = 1;
        CountDownLatch latch = new CountDownLatch(1);

        B b = new B("B", latch);
        A a = new A("C", latch);

        b.start();
        a.start();

        Thread.sleep(10);//simulation of some actual work

        System.out.println("-----------------------------------------------");
        System.out.println(" Now release the latch:");
        System.out.println("-----------------------------------------------");
        latch.countDown();
    }

    static class B extends Thread {

        private CountDownLatch latch;

        public B(String name, CountDownLatch latch) {
            this.latch = latch;
            setName(name);
        }

        @Override
        public void run() {
            try {
                System.out.printf("[ %s ] created, blocked by the latch...\n", getName());
                latch.await();
                System.out.printf("[ %s ] starts at: %s\n", getName(), Instant.now());
                Demo1.k =  Demo1.i;;
                System.out.println(Demo1.i);
            } catch (InterruptedException e) {
                // handle exception
            }
        }
    }

    static class A extends Thread {

        private CountDownLatch latch;

        public A(String name, CountDownLatch latch) {
            this.latch = latch;
            setName(name);
        }

        @Override
        public void run() {
            try {
                System.out.printf("[ %s ] created, blocked by the latch...\n", getName());
                latch.await();
                System.out.printf("[ %s ] starts at: %s\n", getName(), Instant.now());
                Demo1.i = 2;
            } catch (InterruptedException e) {
                // handle exception
            }
        }
    }
}
