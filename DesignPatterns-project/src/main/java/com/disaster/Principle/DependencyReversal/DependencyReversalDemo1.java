package com.disaster.Principle.DependencyReversal;
/*
由这个案例引出 依赖倒转原则
 */
public class DependencyReversalDemo1 {
    public static void main(String[] args) {
        new Person().receive(new Email());
    }
}

class Person{
    public void receive(Email email){
        System.out.println(email.getInfo());
    }
}

class Email{
    public String getInfo(){
        return "电子邮件信息：Email";
    }
}