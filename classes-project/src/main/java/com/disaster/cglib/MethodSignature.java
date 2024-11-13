package com.disaster.cglib;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;

public class MethodSignature {
    public static void main(String[] args) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(PersonService.class);
        enhancer.setCallback((MethodInterceptor) (obj, method, arg, proxy) -> {
            if (method.getDeclaringClass() != Object.class && method.getReturnType() == String.class) {
                return "Hello Tom!";
            } else if (method.getDeclaringClass() != Object.class && method.getReturnType() == Integer.class) {
                return proxy.invokeSuper(obj,arg);
            } else {
                return proxy.invokeSuper(obj, arg);
            }
        });

        PersonService proxy = (PersonService) enhancer.create();
        String sayHello = proxy.sayHello(null);
        System.out.println("sayHello = " + sayHello);
        int lengthOfName = proxy.lengthOfName("Mary1");
        System.out.println("lengthOfName = " + lengthOfName);
    }
}
