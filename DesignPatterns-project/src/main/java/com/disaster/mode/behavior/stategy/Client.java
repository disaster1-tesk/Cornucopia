package com.disaster.mode.behavior.stategy;

/**
 * @ClassName Client
 * @Description TODO
 * @Author Disaster
 * @Date 2021/7/3 12:14
 * @Version 1.0
 **/
public class Client {
    public static void main(String[] args) {
        WildDunk wildDunk = new WildDunk(new GoodFlyStrategy(),new GoodQuackStrategy());
        HomeDunk homeDunk = new HomeDunk(new BadFlyStrategy(),new BadQuackStrategy());
        wildDunk.quack();
        wildDunk.fly();
        homeDunk.quack();
        homeDunk.fly();
    }
}
