package com.disaster.basic.thread;

import lombok.SneakyThrows;

/**
 * 使用join需要保证主线程顺畅执行,否则会报java.lang.InterruptedException错误
 */
public class Join {
    public static void main(String[] args) throws InterruptedException {

//        joinMainInterrupt();
//        join();
    }
    @SneakyThrows
    private static void join()  {
        Thread thread0 = new Thread(() -> {
            for (;;){

            }
        });
        thread0.start();
        //join
        thread0.join();
    }

    //
    private static void joinMainInterrupt() throws InterruptedException {
        Thread mainThread = Thread.currentThread();
        Thread thread0 = new Thread(() -> {
           while (!mainThread.isInterrupted()){

           }
        });

        Thread thread1 = new Thread(() -> {
            try {
                Thread.sleep(2000);
                mainThread.interrupt();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        thread0.start();
        thread1.start();
        thread0.join();
        thread1.join();
    }
}
