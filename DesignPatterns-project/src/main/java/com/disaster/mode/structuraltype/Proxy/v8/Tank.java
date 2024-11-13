package com.disaster.mode.structuraltype.Proxy.v8;


import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Random;

public class Tank implements Movable {

    @Override
    public void move() {
        System.out.println("ClHa...Cla...Cla");
        try {
            Thread.sleep(new Random().nextInt(10000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        final Tank tank = new Tank();
        //reflection 通过二进制字节码分析类的属性和方法
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles","true");
        //System.getProperties().put("jdk.proxy.ProxyGenerator.saveGeneratedFiles", "true");
       /* System.out.println("$Proxy0.class全名: "+Proxy.getProxyClass(Movable.class.getClassLoader(), Movable.class));*/
        Movable m1 = (Movable) Proxy.newProxyInstance(Tank.class.getClassLoader(),
                Tank.class.getInterfaces()
                /*new Class[]{Movable.class}*/,
                new LogInvocationHandler(tank)
        );
        m1.move();
    }
}

class LogInvocationHandler implements InvocationHandler {
    Tank tank;

    public LogInvocationHandler(Tank tank) {
        this.tank = tank;
    }

    public void before() {
        System.out.println("method  start...");
    }

    public void after() {
        System.out.println("method  end!");
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        before();
        Object o = method.invoke(tank, args);
        after();
        return o;
    }
}

interface Movable {
    void move();
}