package com.disaster.mode.behavior.stategy;

/**
 * @ClassName NoFlyStrategy
 * @Description TODO
 * @Author Disaster
 * @Date 2021/7/3 12:02
 * @Version 1.0
 **/
public class NoFlyStrategy implements FlyStrategy {
    @Override
    public void fly() {
        System.out.println("不会飞翔技术的鸭子！");
    }
}
