package com.disaster.basic.lock;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.*;
import java.util.concurrent.locks.ReentrantLock;

public class AbstractQueuedSynchronizer {
    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();
        ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();
//        acquire(lock);
//        acquire(rwLock);
//        acquireInterruptibly(lock);
//        condition(lock);
        noReentrantLock();
    }

    private static void condition(ReentrantLock lock) {
        Condition condition = lock.newCondition();
        new Thread(() -> {
            lock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + " begin await");
                condition.await();
                System.out.println(Thread.currentThread().getName() + " end await");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }, "thread1").start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(() -> {
            lock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + " begin await");
                condition.await();
                System.out.println(Thread.currentThread().getName() + "  end await");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }, "thread2").start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(() -> {
            lock.lock();
            System.out.println(Thread.currentThread().getName() + " begin signal");
            condition.signalAll();
            System.out.println(Thread.currentThread().getName() + " end signal");
        }, "thread3").start();
    }

    private static void acquireInterruptibly(ReentrantLock lock) {
        AtomicReference<Thread> thread1 = new AtomicReference<>();
        AtomicReference<Thread> thread2 = new AtomicReference<>();
        new Thread(() -> {
            thread1.set(Thread.currentThread());
            lock.lock();
        }, "thread1").start();
        new Thread(() -> {
            thread2.set(Thread.currentThread());
            try {
                lock.lockInterruptibly();
                System.out.println("thread2 is unPark");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "thread2").start();
        new Thread(() -> {
            thread1.get().interrupt();
        }, "interruptThread1").start();
        new Thread(() -> {
            thread2.get().interrupt();
        }, "interruptThread2").start();
    }

    private static void acquire(ReentrantReadWriteLock lock) {
        new Thread(() -> {
            //读锁是通过AQS中的state字段的高16作为共享状态所使用，写锁是通过AQS中的state字段的低16位作为共享状态所使用
            ReentrantReadWriteLock.ReadLock readLock = lock.readLock();
            readLock.lock();
            System.out.println(Thread.currentThread().getName() + "  read lock");
        }, "thread1").start();
        new Thread(() -> {
            ReentrantReadWriteLock.ReadLock readLock = lock.readLock();
            readLock.lock();
            System.out.println(Thread.currentThread().getName() + " read lock");
            readLock.unlock();
            System.out.println("readLocks = " + lock.getReadLockCount());
        }, "thread2").start();
        new Thread(() -> {
            ReentrantReadWriteLock.WriteLock writeLock = lock.writeLock();
            writeLock.lock();
        }, "thread3").start();
        new Thread(() -> {
            ReentrantReadWriteLock.WriteLock writeLock = lock.writeLock();
            System.out.println("readLocks = " + lock.getWriteHoldCount());
            System.out.println("writeLockQueue = " + lock.getQueueLength());
            writeLock.lock();
        }, "thread4").start();
    }

    private static void acquire(ReentrantLock lock) {
        new Thread(() -> {
            lock.lock();
            //与synchronizer一样是可重入锁
//            lock.lock();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //将head节点中next节点中的线程进行释放
            lock.unlock();
            System.out.println(Thread.currentThread().getName() + " is locked");
        }, "thread1").start();
        new Thread(() -> {
            lock.lock();
            System.out.println(Thread.currentThread().getName() + " is locked");
        }, "thread2").start();
        new Thread(() -> {
            lock.lock();
            System.out.println(Thread.currentThread().getName() + " is locked");
        }, "thread3").start();
        new Thread(() -> {
            lock.unlock();
        }, "thread4").start();
    }

    private static void noReentrantLock(){
        NoReentrantLock noReentrantLock = new NoReentrantLock();
        new Thread(()->{
            boolean b = noReentrantLock.tryLock();
            if (b){
                System.out.println(Thread.currentThread().getName() + " is locked");
            }else {
                System.out.println(Thread.currentThread().getName() + " isn't locked");
            }
        },"thread1").start();
        new Thread(()->{
            boolean b = noReentrantLock.tryLock();
            if (b){
                System.out.println(Thread.currentThread().getName() + " is locked");
            }else {
                System.out.println(Thread.currentThread().getName() + " isn't locked");
            }
        },"thread2").start();
    }

    static class NoReentrantLock implements Lock, Serializable {

         static class Sync extends java.util.concurrent.locks.AbstractQueuedSynchronizer {
            @Override
            protected boolean tryAcquire(int arg) {
                assert arg == 1;
                if (compareAndSetState(0, 1)) {
                    setExclusiveOwnerThread(Thread.currentThread());
                    return true;
                }
                return false;
            }

            @Override
            protected boolean tryRelease(int arg) {
                assert arg == 1;
                if (getState() == 0) {
                    throw new IllegalMonitorStateException();
                }
                setExclusiveOwnerThread(null);
                setState(0);
                return true;
            }

            @Override
            protected boolean isHeldExclusively() {
                //是否锁已经被持有
                return getState() == 1;
            }

            Condition newCondition() {
                return new ConditionObject();
            }
        }

        private final Sync sync = new Sync();

        @Override
        public void lock() {
            sync.acquire(1);
        }

        @Override
        public void lockInterruptibly() throws InterruptedException {
            sync.acquireInterruptibly(1);
        }

        @Override
        public boolean tryLock() {
            return sync.tryAcquire(1);
        }

        @Override
        public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
            return sync.tryAcquireNanos(1, unit.toNanos(time));
        }

        @Override
        public void unlock() {
            sync.release(1);
        }

        @Override
        public Condition newCondition() {
            return sync.newCondition();
        }
    }


}
