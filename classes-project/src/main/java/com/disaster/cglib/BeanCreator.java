package com.disaster.cglib;

import net.sf.cglib.beans.BeanGenerator;

import java.lang.reflect.Method;

public class BeanCreator {
    public static void main(String[] args)  throws Exception{
        BeanGenerator beanGenerator = new BeanGenerator();

        beanGenerator.addProperty("name", String.class);

        Object myBean = beanGenerator.create();
        Method setter = myBean.getClass().getMethod("setName", String.class);
        setter.invoke(myBean, "some string value set by a cglib");

        Method getter = myBean.getClass().getMethod("getName");
        Object invoke = getter.invoke(myBean);
        System.out.println("invoke = " + invoke);
    }
}
