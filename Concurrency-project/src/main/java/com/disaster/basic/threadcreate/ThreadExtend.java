package com.disaster.basic.threadcreate;

/**
 * 通过继承Thread的方式创建线程
 */
public class ThreadExtend {
    public static void main(String[] args) {
        InnerThread innerThread = new InnerThread();
        innerThread.start();
    }
    
    static class InnerThread extends Thread{
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName()+"启动成功!!");
            super.run();
        }
    }
}
