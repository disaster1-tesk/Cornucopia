package com.disaster.ioc.qualifier;


import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier
@com.disaster.ioc.qualifier.anno.Ship
public class Ship implements Move{
    @Override
    public void move() {
        System.out.println("ship 在水上跑ing...");
    }
}
