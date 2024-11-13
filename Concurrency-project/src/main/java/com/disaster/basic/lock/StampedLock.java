package com.disaster.basic.lock;

public class StampedLock {
    public static void main(String[] args) {
        java.util.concurrent.locks.StampedLock stampedLock = new java.util.concurrent.locks.StampedLock();
        //写锁
        stampedLock.writeLock();
        //悲观锁读锁
        stampedLock.readLock();
        //乐观锁读锁
        stampedLock.tryOptimisticRead();
    }
}
