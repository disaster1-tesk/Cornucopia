package com.disaster.mode.behavior.visitor;

public class Man extends Person{
    public Man(String name, String sex) {
        super(name, sex);
    }

    @Override
    public void accept(Action action) {
        action.getManResult(this);
    }
}
