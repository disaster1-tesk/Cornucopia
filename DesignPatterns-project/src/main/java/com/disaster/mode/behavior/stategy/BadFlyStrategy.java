package com.disaster.mode.behavior.stategy;

/**
 * @ClassName BadFlyStrategy
 * @Description TODO
 * @Author Disaster
 * @Date 2021/7/3 12:02
 * @Version 1.0
 **/
public class BadFlyStrategy implements FlyStrategy {
    @Override
    public void fly() {
        System.out.println("飞翔技术很差的鸭子！");
    }
}
