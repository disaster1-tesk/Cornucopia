package com.disaster.mode.structuraltype.Proxy.v9;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class MyProxyByGclib implements MethodInterceptor {

    /**
     * 维护目标对象
     */
    private Object target;


    public MyProxyByGclib(Object target) {
        this.target = target;
    }

    public Object getProxyInstance() {
        //1. 实例化工具类
        Enhancer en = new Enhancer();
        //2. 设置父类对象
        en.setSuperclass(this.target.getClass());
        //3. 设置回调函数
        en.setCallback(this);
        //4. 创建子类，也就是代理对象
        return en.create();
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("Begin Transaction");
        //执行目标对象的方法
        Object returnValue = method.invoke(target, objects);
        System.out.println("End Transaction");
        return returnValue;
    }

    public static void main(String[] args) {
        //目标对象
        MyServiceImpl myService = new MyServiceImpl();
        //生成代理对象
        MyServiceImpl myProxy = (MyServiceImpl) new MyProxyByGclib(myService).getProxyInstance();
        //调用对象方法
        myProxy.eat();
        myProxy.sleep();
    }
}
