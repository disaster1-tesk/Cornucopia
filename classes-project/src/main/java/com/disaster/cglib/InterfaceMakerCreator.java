package com.disaster.cglib;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.InterfaceMaker;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class InterfaceMakerCreator {
    public static void main(String[] args) throws Exception {
        InterfaceMaker interfaceMaker = new InterfaceMaker();
        //抽取某个类的方法生成接口方法
        interfaceMaker.add(PersonService.class);
        Class<?> targetInterface = interfaceMaker.create();
        for (Method method : targetInterface.getMethods()) {
            System.out.println(method.getName());
        }
        //接口代理并设置代理接口方法拦截
        Object object = Enhancer.create(Object.class, new Class[]{targetInterface}, new MethodInterceptor() {
            @Override
            public Object intercept(Object obj, Method method, Object[] args,
                                    MethodProxy methodProxy) throws Throwable {
                if (method.getName().equals("sayHello")) {
                    System.out.println("filter sayHello ");
                    return "mmmmmmmmm";
                }
                if (method.getName().equals("lengthOfName")) {
                    System.out.println("filter lengthOfName ");
                    return 1111111;
                }
                return "default";
            }
        });
        Method targetMethod1 = object.getClass().getMethod("sayHello", new Class[]{String.class});
        Object invoke = targetMethod1.invoke(object, new Object[]{"hello world"});
        System.out.println("invoke = " + invoke);
        Method targetMethod = object.getClass().getMethod("lengthOfName", new Class[]{String.class});
        Object invoke1 = targetMethod.invoke(object, "hello world");
        System.out.println("invoke1 = " + invoke1);
    }

}
