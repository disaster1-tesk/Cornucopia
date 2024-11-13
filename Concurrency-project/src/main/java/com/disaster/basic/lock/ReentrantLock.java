package com.disaster.basic.lock;

import lombok.SneakyThrows;

import java.util.ArrayList;

public class ReentrantLock {
    private static java.util.concurrent.locks.ReentrantLock failLock = new java.util.concurrent.locks.ReentrantLock(true);
    private static java.util.concurrent.locks.ReentrantLock unFailLock = new java.util.concurrent.locks.ReentrantLock(false);

    @SneakyThrows
    public static void main(String[] args) {
//        failLock();
//        unFailLock();
//        tryLock();
        lockInterruptibly();
    }

    private static void lockInterruptibly() {
        Thread thread1 = new Thread(() -> {
            try {
                //该方法与 lock()方法类似 ，它的不同在于，它对中断进行响应，
                // 就是当前线程在调用该方法时，如果其他线程调用了当前线程的interrupt ()方法， 则当前线程会抛出InterruptedException 异常,然后返回。
                failLock.lockInterruptibly();
                System.out.println(Thread.currentThread().getName() + " is lock");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "thread1");
        Thread thread2 = new Thread(() -> {
            try {
                failLock.lockInterruptibly();
                System.out.println(Thread.currentThread().getName() + " is lock");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "thread2");
        thread1.start();
        thread2.start();
        thread1.interrupt();
    }

    private static void tryLock() {
        //尝试获取锁，如果当前该锁没有被其他线程持有，则当前线程获取该锁井返回 true,否则返回 false
        new Thread(() -> {
            boolean b = failLock.tryLock();
            if (b) {
                System.out.println(Thread.currentThread().getName() + " is lock");
            } else {
                System.out.println(Thread.currentThread().getName() + " isn't lock");
            }
        }, "thread1").start();
        new Thread(() -> {
            boolean b = failLock.tryLock();
            if (b) {
                System.out.println(Thread.currentThread().getName() + " is lock");
            } else {
                System.out.println(Thread.currentThread().getName() + " isn't lock");
            }
        }, "thread2").start();
    }


    private static void failLock() {
        Thread thread1 = new Thread(() -> {
            failLock.lock();
            boolean locked = failLock.isLocked();
            if (locked) {
                System.out.println(Thread.currentThread().getName() + " is lock");
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            failLock.unlock();
            System.out.println(Thread.currentThread().getName() + " is unlock");
        }, "thread1");
        Thread thread2 = new Thread(() -> {
            failLock.lock();
            boolean locked = failLock.isLocked();
            if (locked) {
                System.out.println(Thread.currentThread().getName() + " is lock");
            }
            failLock.unlock();
            System.out.println(Thread.currentThread().getName() + " is unlock");
        }, "thread2");
        Thread thread3 = new Thread(() -> {
            try {
                Thread.sleep(990);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            failLock.lock();
            boolean locked = failLock.isLocked();
            if (locked) {
                System.out.println(Thread.currentThread().getName() + " is lock");
            }
        }, "thread3");
        Thread thread4 = new Thread(() -> {
            try {
                Thread.sleep(990);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            failLock.lock();
            boolean locked = failLock.isLocked();
            if (locked) {
                System.out.println(Thread.currentThread().getName() + " is lock");
            }
        }, "thread4");

        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
    }

    public static void unFailLock() {
        Thread thread1 = new Thread(() -> {
            unFailLock.lock();
            boolean locked = unFailLock.isLocked();
            if (locked) {
                System.out.println(Thread.currentThread().getName() + " is lock");
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            unFailLock.unlock();
            System.out.println(Thread.currentThread().getName() + " is unlock");
        }, "thread1");
        Thread thread2 = new Thread(() -> {
            unFailLock.lock();
            boolean locked = unFailLock.isLocked();
            if (locked) {
                System.out.println(Thread.currentThread().getName() + " is lock");
            }
            unFailLock.unlock();
            System.out.println(Thread.currentThread().getName() + " is unlock");
        }, "thread2");
        Thread thread3 = new Thread(() -> {
            try {
                Thread.sleep(990);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            unFailLock.lock();
            boolean locked = unFailLock.isLocked();
            if (locked) {
                System.out.println(Thread.currentThread().getName() + " is lock");
            }
        }, "thread3");
        Thread thread4 = new Thread(() -> {
            try {
                Thread.sleep(990);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            unFailLock.lock();
            boolean locked = unFailLock.isLocked();
            if (locked) {
                System.out.println(Thread.currentThread().getName() + " is lock");
            }
        }, "thread4");

        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
    }

    /**
     * 线程安全的集合
     * @param <T>
     *
     * @author disaster
     * @version 1.0
     */
    static class ReentrantLockList<T>{
        private ArrayList<T> array = new ArrayList<>();
        //独占锁
        private volatile java.util.concurrent.locks.ReentrantLock lock = new java.util.concurrent.locks.ReentrantLock();

        public void add(T t){
            lock.lock();
            try {
                array.add(t);
            } finally {
                lock.unlock();
            }
        }

        public void remove(T t){
            lock.lock();
            try {
                array.remove(t);
            } finally {
                lock.unlock();
            }
        }

        public T get(int index){
            lock.lock();
            try {
                return array.get(index);
            } finally {
                lock.unlock();
            }
        }
    }
}
