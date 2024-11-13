package com.disaster.Principle.DependencyReversal;
/*
依赖倒转的实现方法一
 */
public class DependencyReversalDemo2 {
    public static void main(String[] args) {
        Person1 person1 = new Person1();
        person1.receive(new Email1());
        person1.receive(new WeiXin());
    }
}

class Person1{
    public void receive(Receive receive){
        System.out.println(receive.getInfo());
    }
}

interface Receive{
    public String getInfo();
}

class Email1 implements Receive{

    @Override
    public String getInfo() {
        return "Email返回值";
    }
}

class WeiXin implements Receive{

    @Override
    public String getInfo() {
        return "WeiXin返回值";
    }
}
