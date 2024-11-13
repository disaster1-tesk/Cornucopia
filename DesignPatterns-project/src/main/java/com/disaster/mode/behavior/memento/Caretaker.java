package com.disaster.mode.behavior.memento;

import java.util.ArrayList;

public class Caretaker {
    private ArrayList<Memento> mementoArrayList = new ArrayList<>();

    public Memento getMemento(int numStatus) {
        return mementoArrayList.get(numStatus);
    }

    public void saveMementoToList(Memento memento) {
        mementoArrayList.add(memento);
    }
}
