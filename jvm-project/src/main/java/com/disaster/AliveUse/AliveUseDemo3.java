package com.disaster.AliveUse;
/*
如果一个接口定义了default方法，那么直接实现或者间接实现该接口的类的初始化，该接口要在其之前被初始化
[Loaded com.disaster.AliveUse.Inf from file:/D:/IDEA/JVM/target/classes/]
[Loaded com.disaster.AliveUse.AliveUseDemo3 from file:/D:/IDEA/JVM/target/classes/]
当虚拟机启动时，用户需要指定一个要执行的主类（包含main（）方法的那个类），虚拟机会先初始化这个主类
当初次使用MethodHandle实例时，初始化该MethodHandle指向的方法所在的类。（设计解析REF_getStatic、REF_putStatic、REF_invokeStatic方法句柄对应的类）。
 */
public class AliveUseDemo3 implements Inf {
    public static void main(String[] args) {
        AliveUseDemo3 test = new AliveUseDemo3();

    }

    @Override
    public void Doing() {

    }

    @Override
    public void do1() {

    }
}

interface Inf{
    default void Doing() {

    }
    void do1();
}