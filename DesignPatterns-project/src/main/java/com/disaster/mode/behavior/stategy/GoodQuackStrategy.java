package com.disaster.mode.behavior.stategy;

/**
 * @ClassName GoodQuackStrategy
 * @Description TODO
 * @Author Disaster
 * @Date 2021/7/3 12:04
 * @Version 1.0
 **/
public class GoodQuackStrategy implements QuackStrategy {
    @Override
    public void quack() {
        System.out.println("叫声好听的鸭子！");
    }
}
