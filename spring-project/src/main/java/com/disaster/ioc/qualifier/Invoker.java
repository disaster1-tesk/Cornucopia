package com.disaster.ioc.qualifier;

import com.disaster.ioc.qualifier.anno.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class Invoker {

    @Autowired
    @Qualifier
    @Car
    private Move move;

    public void move(){
        move.move();
    }
}
