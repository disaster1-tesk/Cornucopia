package com.disaster.basic.lock;

import lombok.SneakyThrows;

import java.util.Queue;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class LockSupport {

    public static void main(String[] args) {
//        park();
//        park1();
//        park2();
//        park3();
//        park4();
//        park5();
//        parkNanos();
//        parkUntil();
//        fifoMutex();
//        park_interrupted();
         park_thread_pool();
    }

    @SneakyThrows
    private static void park_thread_pool() {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        CountDownLatch countDownLatch = new CountDownLatch(2);
        Runnable runnable = () -> {
            countDownLatch.countDown();
            System.out.println("thread1 is running");
            java.util.concurrent.locks.LockSupport.park();
        };
        Runnable runnable2 = () -> {
            System.out.println("thread2 is running");
            java.util.concurrent.locks.LockSupport.park();
        };
        executorService.execute(runnable);
        executorService.execute(runnable2);
        executorService.execute(()->{
            countDownLatch.countDown();
            System.out.println("thread3 is running");
        });
        countDownLatch.await();
        System.out.println("main thread is interrupter");
    }

    private static void park_interrupted() {
        new Thread(()->{
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().isInterrupted());
        }).start();

        java.util.concurrent.locks.LockSupport.park();
    }

    private static void fifoMutex() {
        FIFOMutex fifoMutex = new FIFOMutex();
        Thread thread1 = new Thread(() -> {
            fifoMutex.lock();
        }, "thread1");
        Thread thread2 = new Thread(() -> {
            fifoMutex.lock();
        }, "thread2");
        thread1.start();
        thread2.start();
    }

    static class FIFOMutex {
        private final AtomicBoolean locked = new AtomicBoolean(false);
        private final Queue<Thread> waiters = new ConcurrentLinkedQueue<>();

        public void lock() {
            boolean wasInterrupted = false;
            Thread current = Thread.currentThread();
            waiters.add(current);
            //只有当前线程才能获取锁
            while (waiters.peek() != current || !locked.compareAndSet(false, true)) {
                System.out.println("current thread = "+ current.getName() +" is park");
                java.util.concurrent.locks.LockSupport.park(this);
                if (Thread.interrupted()) {
                    wasInterrupted = true;
                }
            }
            waiters.remove();
            if (wasInterrupted) {
                System.out.println("current thread = "+ current.getName() +" is interrupted");
                current.interrupt();
            }
            System.out.println("current thread = "+ current.getName() +" is not interrupted");
        }

        public void unlock() {
            locked.set(false);
            java.util.concurrent.locks.LockSupport.unpark(waiters.peek());
        }
    }


    private static void park() {
        System.out.println("begin park");
        Thread thread = Thread.currentThread();
        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("begin unPark");
            java.util.concurrent.locks.LockSupport.unpark(thread);
            System.out.println("end unPark");
        }, "thread1").start();
        java.util.concurrent.locks.LockSupport.park();
        System.out.println("end park");
    }

    private static void park1() {
        System.out.println("begin park");
        Thread thread = Thread.currentThread();
        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("begin unPark");
            thread.interrupt();
            System.out.println("end unPark");
        }, "thread1").start();
        java.util.concurrent.locks.LockSupport.park();
        System.out.println("end park");
    }

    private static void park2() {
        System.out.println("begin park");
        //通过unpark获取许可证
        java.util.concurrent.locks.LockSupport.unpark(Thread.currentThread());
        //此时再调用park，由于已经获取许可，因此线程可以正常执行
        java.util.concurrent.locks.LockSupport.park();
        System.out.println("end park");
    }

    @SneakyThrows
    private static void park3() {
        Thread thread = new Thread(() -> {
            System.out.println("child thread begin park");
            java.util.concurrent.locks.LockSupport.park();
            System.out.println("child thread end park");
        }, "thread1");
        thread.start();
        Thread.sleep(1000);
        System.out.println("main thread begin unPark");
        java.util.concurrent.locks.LockSupport.unpark(thread);
    }

    @SneakyThrows
    private static void park4() {
        Thread thread = new Thread(() -> {
            System.out.println("child thread begin park");
            while (!Thread.currentThread().isInterrupted()) {
                java.util.concurrent.locks.LockSupport.park();
            }
            System.out.println("child thread end park");
        }, "thread1");
        thread.start();
        Thread.sleep(1000);
        System.out.println("main thread begin unPark");
        java.util.concurrent.locks.LockSupport.unpark(thread);
    }

    @SneakyThrows
    private static void park5() {
        Thread thread = new Thread(() -> {
            System.out.println("child thread begin park");
            while (!Thread.currentThread().isInterrupted()) {
                java.util.concurrent.locks.LockSupport.park();
            }
            System.out.println("child thread end park");
        }, "thread1");
        thread.start();
        Thread.sleep(1000);
        System.out.println("main thread begin interrupt");
        thread.interrupt();
    }

    private static void parkNanos() {
        Thread thread = new Thread(() -> {
            System.out.println("child thread begin park");
            while (!Thread.currentThread().isInterrupted()) {
                java.util.concurrent.locks.LockSupport.parkNanos(new LockSupport(), 100000);
            }
            System.out.println("child thread end park");
        }, "thread1");
        thread.start();
    }

    private static void parkUntil() {
        Thread thread = new Thread(() -> {
            System.out.println("child thread begin park");
            while (!Thread.currentThread().isInterrupted()) {
                //格林威治时间+deadline
                java.util.concurrent.locks.LockSupport.parkUntil(new LockSupport(), 100000);
            }
            System.out.println("child thread end park");
        }, "thread1");
        thread.start();
    }
}
