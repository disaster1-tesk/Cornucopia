package com.disaster.basic.lock;

import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class IReentrantReadWriteLock {

    public static void main(String[] args) {
        ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();
        readLock(rwLock);
    }

    private static void readLock(ReentrantReadWriteLock rwLock) {
        ReentrantReadWriteLock.ReadLock readLock = rwLock.readLock();
        ReentrantReadWriteLock.WriteLock writeLock = rwLock.writeLock();
        new Thread(() -> {
            writeLock.lock();
            System.out.println(Thread.currentThread().getName() + " is lock");
        }, "thread1").start();
        new Thread(() -> {
            readLock.lock();
            System.out.println(Thread.currentThread().getName() + " is lock");
        }, "thread2").start();
    }

    /**
     * 线程安全的集合，使用readWriteLock吞吐量会更好
     * @param <T>
     *
     * @author disaster
     * @version 1.0
     */
    static class ReentrantLockList<T>{
        private ArrayList<T> array = new ArrayList<>();
        //独占锁
        private final java.util.concurrent.locks.ReentrantReadWriteLock lock = new java.util.concurrent.locks.ReentrantReadWriteLock();
        //读锁
        private final ReentrantReadWriteLock.ReadLock readLock = lock.readLock();
        //写锁
        private final ReentrantReadWriteLock.WriteLock writeLock = lock.writeLock();

        public void add(T t){
            writeLock.lock();
            try {
                array.add(t);
            } finally {
                writeLock.unlock();
            }
        }

        public void remove(T t){
            writeLock.lock();
            try {
                array.remove(t);
            } finally {
                writeLock.unlock();
            }
        }

        public T get(int index){
            readLock.lock();
            try {
                return array.get(index);
            } finally {
                readLock.unlock();
            }
        }
    }
}
