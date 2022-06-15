package com.disaster.ClassLoader;

import org.junit.Test;
/*
clinit<>()方法中的线程安全问题，clinit方法只能被一个线程所执行
 */
public class ClassLoaderDemo2 {
    public static void main(String[] args) {
        ThreadLoader thread1 = new ThreadLoader("1","A");
        thread1.start();
        ThreadLoader thread2 = new ThreadLoader("2", "B");
        thread2.start();
    }
}
class A {
    static {
        try {
            Thread.sleep(1000);
             Class.forName("com.disaster.ClassLoader.B");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println("加载A类");
        }
    }
}

class B {
    static {
        try {
            Thread.sleep(1000);
            Class.forName("com.disaster.ClassLoader.B");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println("加载B类");
        }
    }
}
 class ThreadLoader extends Thread{
    private String flag;

    public ThreadLoader(String name, String flag) {
        super(name);
        this.flag = flag;
    }
    @Override
    public void run() {
        try {
            Class.forName("com.disaster.ClassLoader."+flag);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }finally {
            System.out.println(Thread.currentThread().getName()+"---->"+flag+"---->end");
        }
    }
}