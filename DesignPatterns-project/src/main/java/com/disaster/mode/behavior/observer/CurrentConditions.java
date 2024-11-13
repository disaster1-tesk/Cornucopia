package com.disaster.mode.behavior.observer;

/**
 * @author Lenovo
 */
public class CurrentConditions implements Observer{
    private float temperature;
    private float pressure;
    private float humidity;

    @Override
    public void update(float temperature, float pressure, float humidity){
        this.temperature = temperature;
        this.pressure = pressure;
        this.humidity = humidity;
        display();
    }

    private void display() {
        System.out.println("*****Today mTemperature:"+temperature+"*****");
        System.out.println("*****Today mPressure:"+pressure+"*****");
        System.out.println("*****Today mHumidity:"+humidity+"*****");
    }

}
