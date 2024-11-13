package com.disaster.mode.behavior.stategy;

/**
 * @ClassName BadQuackStrategy
 * @Description TODO
 * @Author Disaster
 * @Date 2021/7/3 12:04
 * @Version 1.0
 **/
public class BadQuackStrategy implements QuackStrategy {
    @Override
    public void quack() {
        System.out.println("叫声难听的鸭子！");
    }
}
