package com.disaster.basic.test;

import lombok.SneakyThrows;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 多线程环境下的线程虚拟唤醒
 */
public class SpuriousWakeup {
    private static ReentrantLock lock = new ReentrantLock();
    private static Condition hasApple = lock.newCondition();
    private static volatile int nApple;

    @SneakyThrows
    public static void main(String[] args) {
//        spuriousWakeup();
        noSpuriousWakeup();
    }

    private static void noSpuriousWakeup() throws InterruptedException {
        new Thread(() -> {
            lock.lock();
            try {
                while (nApple == 0) {
                    System.out.println("{"+Thread.currentThread().getName()+"}:"+"没苹果，我先休息会儿，苹果来了我再醒...");
                    hasApple.await();
                }
                nApple -= 1;
                System.out.println("{"+Thread.currentThread().getName()+"}:"+"哇，苹果来了，我吃掉了...");
                System.out.println("{"+Thread.currentThread().getName()+"}:"+"现在苹果还有 " + nApple + " 个...");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }, "萧炎").start();
        new Thread(() -> {
            lock.lock();
            try {
                while (nApple == 0) {
                    System.out.println("{"+Thread.currentThread().getName()+"}:"+"没苹果，我先休息会儿，苹果来了我再醒...");
                    hasApple.await();
                }
                nApple -= 1;
                System.out.println("{"+Thread.currentThread().getName()+"}:"+"哇，苹果来了，我吃掉了...");
                System.out.println("{"+Thread.currentThread().getName()+"}:"+"现在苹果还有 " + nApple + " 个...");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }, "唐三").start();

        Thread.sleep(1000);

        new Thread(() -> {
            lock.lock();
            try {
                System.out.println("{"+Thread.currentThread().getName()+"}:"+"我来送苹果了，但只有一个哦...");
                nApple = 1;
                hasApple.signalAll();
            } finally {
                lock.unlock();
            }
        }, "萧薰儿").start();
    }

    private static void spuriousWakeup() throws InterruptedException {
        new Thread(() -> {
            lock.lock();
            try {
                if (nApple == 0) {
                    System.out.println("{"+Thread.currentThread().getName()+"}:"+"没苹果，我先休息会儿，苹果来了我再醒...");
                    hasApple.await();
                }
                nApple -= 1;
                System.out.println("{"+Thread.currentThread().getName()+"}:"+"哇，苹果来了，我吃掉了...");
                System.out.println("{"+Thread.currentThread().getName()+"}:"+"现在苹果还有 " + nApple + " 个...");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }, "萧炎").start();
        new Thread(() -> {
            lock.lock();
            try {
                if (nApple == 0) {
                    System.out.println("{"+Thread.currentThread().getName()+"}:"+"没苹果，我先休息会儿，苹果来了我再醒...");
                    hasApple.await();
                }
                nApple -= 1;
                System.out.println("{"+Thread.currentThread().getName()+"}:"+"哇，苹果来了，我吃掉了...");
                System.out.println("{"+Thread.currentThread().getName()+"}:"+"现在苹果还有 " + nApple + " 个...");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }, "唐三").start();

        Thread.sleep(1000);

        new Thread(() -> {
            lock.lock();
            try {
                System.out.println("{"+Thread.currentThread().getName()+"}:"+"我来送苹果了，但只有一个哦...");
                nApple = 1;
                hasApple.signalAll();
            } finally {
                lock.unlock();
            }
        }, "萧薰儿").start();
    }
}
