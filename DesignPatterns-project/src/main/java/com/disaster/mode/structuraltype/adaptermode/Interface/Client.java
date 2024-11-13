package com.disaster.mode.structuraltype.adaptermode.Interface;

public class Client {
    public static void main(String[] args) {
        Cat cat = new Cat();
        new Dog(cat).dogBarking();

    }
}
