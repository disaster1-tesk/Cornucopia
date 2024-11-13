package com.disaster.ioc.qualifier;


import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier
@com.disaster.ioc.qualifier.anno.Car
public class Car implements Move{
    @Override
    public void move() {
        System.out.println("car 在路上跑ing....");
    }
}
