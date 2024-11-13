package com.disaster.mode.behavior.observer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/*
类是核心
    1.包含最新的天气情况信息
    2.含有观察者集合，使用ArrayList管理
    3.当数据更新时，就主动的调用ArrayList.通知所有的接入方，这样他们（接入方）就看到最新的信息
 */
public class WeatherData implements Subject {
    private float temperature;
    private float pressure;
    private float humidity;
    private List<Observer> observerList = new ArrayList<>();

    public WeatherData() {
    }

    public float getTemperature() {
        return temperature;
    }

    public float getPressure() {
        return pressure;
    }

    public float getHumidity() {
        return humidity;
    }

    public void dataChange() {
        notifyObservers();
    }
    public void setData(float temperature,float pressure,float humidity){
        this.temperature =temperature;
        this.pressure =pressure;
        this.humidity = humidity;
        dataChange();
    }
    @Override
    public void registerObserver(Observer o) {
        observerList.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
        if (observerList.contains(o)){
            observerList.remove(o);
        }
    }

    @Override
    public void notifyObservers() {
        Iterator<Observer> iterator = observerList.iterator();
        while (iterator.hasNext()) {
            iterator.next().update(getTemperature(), getPressure(), getHumidity());
        }
    }
}
