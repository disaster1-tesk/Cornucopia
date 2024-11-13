package com.disaster.mode.structuraltype.Proxy.v9;

public class MyServiceImpl implements MyService {

    @Override
    public void eat() {
        System.out.println("一日三餐");
    }

    @Override
    public void sleep() {
        System.out.println("每天八小时睡眠，不然会猝死");
    }

}
