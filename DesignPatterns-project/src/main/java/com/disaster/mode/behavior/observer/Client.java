package com.disaster.mode.behavior.observer;

public class Client {
    public static void main(String[] args) {
        //创建一个WeatherData
        WeatherData weatherData = new WeatherData();
        CurrentConditions currentConditions = new CurrentConditions();
        weatherData.registerObserver(currentConditions);
        System.out.println("通知各个注册的观察者，看看信息");
        weatherData.setData(10f,20f,30f);
        weatherData.removeObserver(currentConditions);
    }
}
