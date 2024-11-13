package com.disaster.mode.behavior.observer.observable;


import java.util.Observable;

/**
 * @ClassName ObservaleTest
 * @Description TODO
 * @Author Disaster
 * @Date 2021/7/5 13:55
 * @Version 1.0
 **/
public class ObservableTest extends Observable {
    private String ClickMode;

    public String getClickMode() {
        return ClickMode;
    }

    public void setClickMode(String clickMode) {
        ClickMode = clickMode;
        this.setChanged();
        this.notifyObservers(clickMode);
    }
}
