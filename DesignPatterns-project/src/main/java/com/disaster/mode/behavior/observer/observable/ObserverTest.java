package com.disaster.mode.behavior.observer.observable;

import java.util.Observable;
import java.util.Observer;

/**
 * @ClassName ObserverTest
 * @Description TODO
 * @Author Disaster
 * @Date 2021/7/5 13:55
 * @Version 1.0
 **/
public class ObserverTest implements Observer {
    @Override
    public void update(Observable o, Object arg) {
        if (arg.toString().equals("Click Double")){
            System.out.println("您双击了！");
        }else{
            System.out.println("无法识别！");
        }
    }
}
