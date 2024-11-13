package com.disaster.basic.threadpool;

import lombok.SneakyThrows;

import java.util.concurrent.*;

/**
 * 线程池主要解决两个问题 :
 * 一是当执行大量异步任务时线程池能够提供较好的性能。 在不使用线程池时，每当需要执行异步任务时直接 new 一个线程来运行，而线程的创建和销毁是需要开销的。 线程池里面的线程是可复用的，不需要每次执行异步任务时都重新创建和销毁线程 。
 * 二是线程i也提供了 一种资源限制和管理的手段，比如可以限制线程的个数，动态新增线程等 。每个 ThreadPoolExecutor 也保留了一些基本的统计数据，比如当前线程池完成的任务数目等。
 * <p>
 * 线程池的状态：
 * 1.RUNNING :接受新任务并且处理阻塞队列里的任务
 * 2.SHUTDOWN :拒绝新任务但是处理阻塞队列里的任务
 * 3.STOP :拒绝新任务并且抛弃阻塞队列里的任务 ，同时会中断正在处理的任务
 * 4.TIDYING :所有任务都执行完(包含阻塞队列里面的任务)后当前线程池活动线程数为 0， 将要调用 terminated 方法
 * 5.TERMINATED :终止状态 。terminated方法调用完成以后的状态
 * 线程池的状态转换：
 * RUNNING --> SHUTDOWN ：显式调用 shutdown()方法，或者隐式调用了 finalize()方法里面的 shutdown()方法
 * RUNNING/SHUTDOWN --> STOP: 显式调用 shutdownNow() 方法
 * SHUTDOWN --> TIDYING : 当线程池和任务队列都为空时
 * STOP --> TIDYING : 当线程池为空时
 * TIDYING --> TERMINATED : 当 terminated() hook 方法执行完成时
 * <p>
 * 线程池参数：
 * int corePoolSize ：线程池核心线程个数
 * int maximumPoolSize ：线程池最大线程数量
 * long keepAliveTime ： 存活时间，如果当前线程池中的线程数量比核心线程数量多，并且是闲置状态， 则这些闲置的线程能存活的最大时间
 * TimeUnit unit ：存活时间的时间单位
 * BlockingQueue<Runnable> workQueue ：用于保存等待执行的任务的阻 塞 队列，比如基于数组的有界ArrayBlockingQueue、基于链表的无界 LinkedBlockingQueue、最多只有一个元素的同步队列 SynchronousQueue 及优先级队列 PriorityBlockingQueue 等
 * ThreadFactory threadFactory ：创建线程的工厂
 * RejectedExecutionHandler handler ：饱和策略，当队列满并且线程个数达到 maximunPoolSize后采取的策略， 比如 AbortPolicy (抛出异常〉、 CallerRunsPolicy (使用调用者所在 线程来运行任务)、 DiscardOldestPolicy (调用 poll丢弃一个任务，执行当前任务) 及 DiscardPolicy (默默丢弃，不抛出异常〉
 */
public class ThreadPoolExecutor {
    public static void main(String[] args) {
//        InnerThreadPoolExecutor();
//        shutdown();
//        shutdownNow();
//        awaitTermination();
        threadPoolExecutor();
    }

    private static void threadPoolExecutor(){
        ExecutorService executorService =
                new java.util.concurrent.ThreadPoolExecutor(1, 2, 30,
                        TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(1),
                        Executors.defaultThreadFactory(), new java.util.concurrent.ThreadPoolExecutor.AbortPolicy());
        //核心线程为1，则会创建一个线程去执行run方法，并一直占据这个核心线程
        executorService.execute(() -> {
            System.out.println("hello world1 begin");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+" is interrupted");
        });
        //由于核心线程已经被占据，再来一个线程则将此线程放入工作队列中
        executorService.execute(() -> {
            System.out.println("hello world2");
        });
    }

    @SneakyThrows
    private static void awaitTermination() {
        ExecutorService executorService =
                new java.util.concurrent.ThreadPoolExecutor(1, 2, 30,
                        TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(1),
                        Executors.defaultThreadFactory(), new java.util.concurrent.ThreadPoolExecutor.AbortPolicy());
        //核心线程为1，则会创建一个线程去执行run方法，并一直占据这个核心线程
        executorService.execute(() -> {
            System.out.println("hello world1 begin");
            System.out.println("hello world1 end");
        });
        System.out.println("awaitTermination begin");
        executorService.shutdownNow();
        executorService.awaitTermination(3,TimeUnit.SECONDS);
        System.out.println("awaitTermination end");
    }

    private static void shutdownNow() {
        ExecutorService executorService =
                new java.util.concurrent.ThreadPoolExecutor(1, 2, 30,
                        TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(1),
                        Executors.defaultThreadFactory(), new java.util.concurrent.ThreadPoolExecutor.AbortPolicy());
        //核心线程为1，则会创建一个线程去执行run方法，并一直占据这个核心线程
        executorService.execute(() -> {
            System.out.println("hello world1 begin");
            while (!Thread.currentThread().isInterrupted()){

            }
            System.out.println(Thread.currentThread().getName()+" is interrupted");
        });
        //由于核心线程已经被占据，再来一个线程则将此线程放入工作队列中
        executorService.execute(() -> {
            System.out.println("hello world2");
        });
        System.out.println("begin executorService shutdown...");
        //shutdown：拒绝提交新的线程，中断正在执行的请求，并且会丢弃工作队列里面的任务
        executorService.shutdownNow();
        System.out.println("end executorService shutdown...");
    }

    private static void shutdown() {
        ExecutorService executorService =
                new java.util.concurrent.ThreadPoolExecutor(1, 2, 30,
                        TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(1),
                        Executors.defaultThreadFactory(), new java.util.concurrent.ThreadPoolExecutor.AbortPolicy());
        //核心线程为1，则会创建一个线程去执行run方法，并一直占据这个核心线程
        executorService.execute(() -> {
            System.out.println("hello world1 begin");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("hello world1 end");
        });
        //由于核心线程已经被占据，再来一个线程则将此线程放入工作队列中
        executorService.execute(() -> {
            System.out.println("hello world2");
        });
        System.out.println("begin executorService shutdown...");
        //shutdown：拒绝提交新的线程，等待正在执行的线程执行完毕，同时执行完队列中的线程
        executorService.shutdown();
        System.out.println("end executorService shutdown...");
    }

    private static void InnerThreadPoolExecutor() {
        ExecutorService executorService =
                new InnerThreadPoolExecutor(1, 2, 30,
                        TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(1),
                        Executors.defaultThreadFactory(), new java.util.concurrent.ThreadPoolExecutor.AbortPolicy());
        //核心线程为1，则会创建一个线程去执行run方法，并一直占据这个核心线程
        executorService.execute(() -> {
            System.out.println(-1 << Integer.SIZE - 3);
            System.out.println("hello world1");
            while (true) {

            }
        });
        //由于核心线程已经被占据，再来一个线程则将此线程放入工作队列中
        executorService.execute(() -> {
            System.out.println(-1 << Integer.SIZE - 3);
            System.out.println("hello world2");
        });
        //由于核心线程与队列都已经满了，则会判断线程池中的最大线程数，如果目前小于最大核心线程数，会创建一个新的非核心线程去执行run方法
        executorService.execute(() -> {
            System.out.println(-1 << Integer.SIZE - 3);
            System.out.println("hello world3");
        });
    }

    static class InnerThreadPoolExecutor extends java.util.concurrent.ThreadPoolExecutor {

        public InnerThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
            super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
        }

        public InnerThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory) {
            super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory);
        }

        public InnerThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, RejectedExecutionHandler handler) {
            super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, handler);
        }

        public InnerThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory, RejectedExecutionHandler handler) {
            super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory, handler);
        }

        @Override
        protected void beforeExecute(Thread t, Runnable r) {
            System.out.println(t.getName() + " is start runWork");
            r.run();
        }

        @Override
        protected void afterExecute(Runnable r, Throwable t) {
            super.afterExecute(r, t);
        }

        @Override
        public boolean prestartCoreThread() {
            boolean result = super.prestartCoreThread();
            System.out.println("all core thread is started");
            return result;
        }

        @Override
        protected void terminated() {
            //当调用shutdown或者shutdownnow方法时执行
            System.out.println(this.getClass().getName() + "is terminated");
            super.terminated();
        }

        @Override
        protected void finalize() {
            super.finalize();
        }


    }
}
