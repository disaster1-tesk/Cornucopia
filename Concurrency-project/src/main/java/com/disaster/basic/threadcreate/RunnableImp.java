package com.disaster.basic.threadcreate;

/**
 * 通过实现Runnable接口创建线程
 */
public class RunnableImp {
    public static void main(String[] args) {
        Thread thread = new Thread(new InnerRunnableImpl());
        thread.start();
    }

    static class InnerRunnableImpl implements Runnable {

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + "启动成功!!");
        }
    }
}
