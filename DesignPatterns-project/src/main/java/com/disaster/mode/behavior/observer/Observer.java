package com.disaster.mode.behavior.observer;

//观察者接口，有观察者来实现
public interface Observer {
    void update(float temperature, float pressure, float humidity);
}
