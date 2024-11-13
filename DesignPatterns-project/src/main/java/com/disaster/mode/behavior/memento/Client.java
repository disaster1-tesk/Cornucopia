package com.disaster.mode.behavior.memento;

public class Client {
    public static void main(String[] args) {
        Caretaker caretaker = new Caretaker();
        GameRole gameRole = new GameRole(100, 200);
        System.out.println("============大战前=============");
        gameRole.showRoleStatus();
        caretaker.saveMementoToList(gameRole.createMemento());
        gameRole.setVit(10);
        gameRole.setDef(399);
        caretaker.saveMementoToList(gameRole.createMemento());
        System.out.println("============大战后=============");
        gameRole.showRoleStatus();
        gameRole.recoverStatusFromMemento(caretaker.getMemento(0));
        System.out.println("===========恢复后=============");
        gameRole.showRoleStatus();
    }
}
