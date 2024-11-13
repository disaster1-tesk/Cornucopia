package com.disaster.cglib;

import net.sf.cglib.proxy.Enhancer;

/**
 * Hello world!
 */
public class FixedValue {
    public static void main(String[] args) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(PersonService.class);
        enhancer.setCallback((net.sf.cglib.proxy.FixedValue) () -> "Hello Tom1!");
        PersonService proxy = (PersonService) enhancer.create();

        String res = proxy.sayHello(null);
        System.out.println("res = " + res);
    }
}
