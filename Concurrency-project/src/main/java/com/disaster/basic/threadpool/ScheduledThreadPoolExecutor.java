package com.disaster.basic.threadpool;

import java.util.concurrent.TimeUnit;

public class ScheduledThreadPoolExecutor {
    public static void main(String[] args) {
        java.util.concurrent.ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = new java.util.concurrent.ScheduledThreadPoolExecutor(1);
        //此方法与ThreadPoolExecutor一致
        scheduledThreadPoolExecutor.execute(() -> {

            System.out.println("execute is running...");

        });
        //该方法的作用是提交一个延迟执行的任务 ， 任务从提交时间算起延迟单位为 unit 的delay 时间后开始执行 。 提交的任务不是周期性任务，任务只会执行 一次
        scheduledThreadPoolExecutor.schedule(() -> {

            System.out.println("schedule is running...");

        }, 2, TimeUnit.SECONDS);

        //每秒执行一次
        scheduledThreadPoolExecutor.scheduleAtFixedRate(() -> {

            System.out.println("scheduleAtFixedRate is running...");

        }, 0, 2, TimeUnit.SECONDS);

        //延迟1000秒执行
        scheduledThreadPoolExecutor.scheduleWithFixedDelay(() -> {

            System.out.println("scheduleWithFixedDelay is running..");

        }, 0, 2, TimeUnit.SECONDS);
    }
}
