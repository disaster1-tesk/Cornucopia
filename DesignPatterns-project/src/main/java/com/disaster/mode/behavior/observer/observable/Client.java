package com.disaster.mode.behavior.observer.observable;

/**
 * @ClassName Client
 * @Description TODO
 * @Author Disaster
 * @Date 2021/7/5 13:55
 * @Version 1.0
 **/
public class Client {
    public static void main(String[] args) {
        System.out.println(Integer.MAX_VALUE);
        ObservableTest observableTest = new ObservableTest();
        ObserverTest observerTest = new ObserverTest();
        observableTest.addObserver(observerTest);
        observableTest.setClickMode("Click Double");
    }
}
