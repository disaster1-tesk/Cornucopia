package com.disaster.mode.behavior.stategy;

/**
 * @ClassName NoQuackStrategy
 * @Description TODO
 * @Author Disaster
 * @Date 2021/7/3 12:04
 * @Version 1.0
 **/
public class NoQuackStrategy implements QuackStrategy {
    @Override
    public void quack() {
        System.out.println("不会叫的鸭子");
    }
}
