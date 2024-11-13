package com.disaster.mode.createtype.singletonmode;

public class SingletonModeDemo1  {
    //防止此对象被实例化
    private void SingletonModeDemo1(){

    }
    //饿汉式
    /*private final static SingletonModeDemo1 MysingletonMode = new SingletonModeDemo1();
    public static SingletonModeDemo1 getMysingletonMode() {
        return MysingletonMode;
    }A
    private  static SingletonModeDemo1 MysingletonMode1 = null;
    {
        MysingletonMode1 = new SingletonModeDemo1();
    }
    public static SingletonModeDemo1 getInstance(){
        return MysingletonMode1;
    }*/
    //懒汉式
    /*private static SingletonModeDemo1 singletonModeDemo1;
    public static SingletonModeDemo1 getInstance(){
        if (singletonModeDemo1==null){
            singletonModeDemo1 = new SingletonModeDemo1();
        }
        return singletonModeDemo1;
    }*/
    //懒汉式（线程安全）
    /*private static SingletonModeDemo1 singletonModeDemo1;
    public  synchronized static SingletonModeDemo1 getInstance(){
        if (singletonModeDemo1 == null) {
            singletonModeDemo1 = new SingletonModeDemo1();
        }
        return singletonModeDemo1;
    }*/
    //DCL  原子性，可见性、有序性
   /* private volatile static SingletonModeDemo1 singletonMode=null;

    //不加volatile可能会出现指令重排序，导致其他线程获取的这个对象是一个没有没有new完全的对象
    public static SingletonModeDemo1 getInstance() {
        if (singletonMode == null) {
            synchronized (SingletonModeDemo1.class) {
                if (singletonMode == null) {
                    singletonMode = new SingletonModeDemo1();
                }
            }
        }
        return singletonMode;
    }*/
   //静态内部类
   /* private static class Instance{
        public static SingletonModeDemo1 singletonModeDemo1= new SingletonModeDemo1();
    }
    public static SingletonModeDemo1 getInstance(){
        return Instance.singletonModeDemo1;
    }*/
   //枚举
 public  enum Singleton{
        INSTANCE;
        public void doSomething(){
            System.out.println("枚举");
        }

    }
    public static void main(String[] args) {
//        SingletonModeDemo1 mysingletonMode = SingletonModeDemo1.getMysingletonMode();
//        SingletonModeDemo1 mysingletonMode1 = SingletonModeDemo1.getMysingletonMode();
//        System.out.println(mysingletonMode == mysingletonMode1?"相等":"不相等");
            Singleton.INSTANCE.doSomething();
    }
}
