package com.disaster.mode.behavior.visitor;

public class Woman extends Person{
    public Woman(String name, String sex) {
        super(name, sex);
    }

    @Override
    public void accept(Action action) {
        action.getWomanResult(this);
    }
}
