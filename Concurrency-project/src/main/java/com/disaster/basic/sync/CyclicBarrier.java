package com.disaster.basic.sync;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CyclicBarrier {
    public static void main(String[] args) {
//        await();
        queueAwait();
    }

    private static void queueAwait() {
        java.util.concurrent.CyclicBarrier cyclicBarrier = new java.util.concurrent.CyclicBarrier(2, () -> {
            System.out.println(Thread.currentThread().getName() + " task1 merge result");
        });
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.submit(() -> {
            try {
                System.out.println(Thread.currentThread().getName()+" step1");
                cyclicBarrier.await();
                System.out.println(Thread.currentThread().getName()+" step2");
                cyclicBarrier.await();
                System.out.println(Thread.currentThread().getName()+" step3");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        });
        executorService.submit(() -> {
            try {
                System.out.println(Thread.currentThread().getName()+" step1");
                cyclicBarrier.await();
                System.out.println(Thread.currentThread().getName()+" step2");
                cyclicBarrier.await();
                System.out.println(Thread.currentThread().getName()+" step3");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        });
        executorService.shutdown();
    }

    private static void await() {
        java.util.concurrent.CyclicBarrier cyclicBarrier = new java.util.concurrent.CyclicBarrier(2, () -> {
            System.out.println(Thread.currentThread().getName() + " task1 merge result");
        });
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.submit(() -> {
            try {
                System.out.println(Thread.currentThread().getName()+" task-1");
                System.out.println(Thread.currentThread().getName()+" enter in barrier");
                cyclicBarrier.await();
                System.out.println(Thread.currentThread().getName()+" enter out barrier");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        });
        executorService.submit(() -> {
            try {
                System.out.println(Thread.currentThread().getName()+" task-2");
                System.out.println(Thread.currentThread().getName()+" enter in barrier");
                cyclicBarrier.await();
                System.out.println(Thread.currentThread().getName()+" enter out barrier");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        });
        executorService.shutdown();
    }
}
