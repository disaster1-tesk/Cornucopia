package com.disaster.mode.behavior.stategy;

/**
 * @ClassName GoodFlyStrategy
 * @Description TODO
 * @Author Disaster
 * @Date 2021/7/3 12:01
 * @Version 1.0
 **/
public class GoodFlyStrategy implements FlyStrategy {
    @Override
    public void fly() {
        System.out.println("飞翔技术非常高超的鸭子！");
    }
}
