package com.disaster.aop.aspectj;

public class DefaultEncoreable implements Encoreable{
    @Override
    public void performEncore() {
        System.out.println(String.format("%s 执行ing...",this.getClass().getSimpleName()));
    }
}
