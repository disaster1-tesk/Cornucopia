package com.disaster.mode.behavior.stategy;

/**
 * @ClassName Dunk
 * @Description TODO
 * @Author Disaster
 * @Date 2021/7/3 11:54
 * @Version 1.0
 **/
public abstract class Dunk {
    private FlyStrategy flyStrategy;
    private QuackStrategy quackStrategy;

    public Dunk(FlyStrategy flyStrategy, QuackStrategy quackStrategy) {
        this.flyStrategy = flyStrategy;
        this.quackStrategy = quackStrategy;
    }

    public void quack() throws NullPointerException {
        if (quackStrategy==null){
            throw new NullPointerException();
        }
        quackStrategy.quack();
    }
    public void fly(){
        if (flyStrategy==null){
            throw new NullPointerException();
        }
        flyStrategy.fly();
    }

}
