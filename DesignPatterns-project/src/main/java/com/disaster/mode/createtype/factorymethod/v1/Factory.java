package com.disaster.mode.createtype.factorymethod.v1;

public abstract class Factory {
    public abstract MoveAble getVehicle();
    public MoveAble createVehicle(){
        return getVehicle();
    }
}
