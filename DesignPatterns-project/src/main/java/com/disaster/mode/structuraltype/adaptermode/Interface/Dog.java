package com.disaster.mode.structuraltype.adaptermode.Interface;

public class Dog implements Bark{
    private Catch aCatch;
    public Dog(Catch aCatch){
        this.aCatch = aCatch;
    }
    @Override
    public void dogBarking() {
        aCatch.catching();
    }
}
