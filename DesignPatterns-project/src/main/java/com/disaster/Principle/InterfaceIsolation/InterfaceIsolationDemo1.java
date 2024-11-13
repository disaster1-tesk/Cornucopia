package com.disaster.Principle.InterfaceIsolation;

public class InterfaceIsolationDemo1 {
    public static void main(String[] args) {
        A a = new A();
        C c = new C();
        B b = new B();
        D d = new D();
        a.depend1(b);
        a.depend2(b);
        a.depend3(b);
        System.out.println("==============================");
        c.depend1(d);
        c.depend4(d);
        c.depend5(d);
    }
}

interface Interface {
    void operation1();

    void operation2();

    void operation3();

    void operation4();

    void operation5();
}

class A {
    public void depend1(Interface inf) {
        inf.operation1();
    }

    public void depend2(Interface inf) {
        inf.operation2();
    }

    public void depend3(Interface inf) {
        inf.operation3();
    }
}

class B implements Interface {

    @Override
    public void operation1() {
        System.out.println("B的operation1");
    }

    @Override
    public void operation2() {
        System.out.println("B的operation2");
    }

    @Override
    public void operation3() {
        System.out.println("B的operation3");
    }

    @Override
    public void operation4() {
        System.out.println("B的operation4");
    }

    @Override
    public void operation5() {
        System.out.println("B的operation5");
    }
}

class C {
    public void depend1(Interface inf) {
        inf.operation1();
    }

    public void depend4(Interface inf) {
        inf.operation4();
    }

    public void depend5(Interface inf) {
        inf.operation5();
    }
}

class D implements Interface {

    @Override
    public void operation1() {
        System.out.println("D的operation1");
    }

    @Override
    public void operation2() {
        System.out.println("D的operation2");
    }

    @Override
    public void operation3() {
        System.out.println("D的operation3");
    }

    @Override
    public void operation4() {
        System.out.println("D的operation4");
    }

    @Override
    public void operation5() {
        System.out.println("D的operation5");
    }
}