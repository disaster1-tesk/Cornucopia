package com.disaster.mode.behavior.visitor;

public class Client {
    public static void main(String[] args) {
        //创建ObjectStructure
        ObjectStructure objectStructure = new ObjectStructure();
        objectStructure.attach(new Man("com/disaster","男"));
        objectStructure.attach(new Man("disaster1","男"));
        objectStructure.attach(new Man("disaster3","女"));
        Action success = new Success();
        objectStructure.display(success);
    }
}
