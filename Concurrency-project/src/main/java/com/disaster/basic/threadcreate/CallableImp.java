package com.disaster.basic.threadcreate;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 通过callable接口加futuretask实现线程，此方式有返回值信息
 */
public class CallableImp {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<String> futureTask = new FutureTask<>(new InnerCallableImp());
        Thread thread = new Thread(futureTask);
        thread.start();
        thread.interrupt();
        String s = futureTask.get();
        System.out.println("s = " + s);
    }

    static class  InnerCallableImp implements Callable<String> {

        @Override
        public String call() throws Exception {
            while (!Thread.currentThread().isInterrupted()){

            }
            return Thread.currentThread().getName()+"创建成功！！";
        }
    }
}
