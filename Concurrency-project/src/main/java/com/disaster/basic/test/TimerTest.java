package com.disaster.basic.test;

import java.util.Timer;
import java.util.TimerTask;

/**
 * 当一个 Timer 运行多个TimerTask 时，只要其中一个 TimerTask 在执行中向run 方法外抛出了异常 ，则其他任务也会自动终止
 */
public class TimerTest {
    public static void main(String[] args) {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("task1 is running");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                throw new RuntimeException("test timer error");
            }
        }, 500);
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                for(;;){
                    System.out.println("task2 is running");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        },1000);
    }
}
