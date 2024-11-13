package com.disaster.mode.createtype.factorymethod;
//prepare，bake，cut，box
public abstract class Pizza {
    protected abstract void prepare();
    protected abstract void bake();
    protected abstract void cut();
    protected abstract void box();
}
