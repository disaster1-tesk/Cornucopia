package com.disaster.mode.structuraltype.Proxy.v8;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;


public class Client {
    public static void main(String[] args) {
        get(Thread.currentThread().getContextClassLoader(),
                new Class[]{Tank.class},
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        return null;
                    }
                });
    }
    public static void get(ClassLoader loader,
                           Class<?>[] interfaces,
                           InvocationHandler h){
        System.out.println("执行了方法...");
    }
}
