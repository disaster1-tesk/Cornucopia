package com.disaster.Principle.InterfaceIsolation;
/*
通过接口隔离原则进行相关的优化
 */
public class InterfaceIsolationDemo2 {
    public static void main(String[] args) {
        A1 a1 = new A1();
        C1 c1 = new C1();
        a1.depend1(new B1());
        a1.depend2(new B1());
        a1.depend3(new B1());
        c1.depend1(new D1());
        c1.depend4(new D1());
        c1.depend5(new D1());
    }
}

interface Interface1 {
    void operation1();
}

interface Interface2 {
    void operation2();
    void operation3();
}

interface Interface3{
    void operation4();
    void operation5();
}

class A1 {
    public void depend1(Interface1 inf) {
        inf.operation1();
    }

    public void depend2(Interface2 inf) {
        inf.operation2();
    }

    public void depend3(Interface2 inf) {
        inf.operation3();
    }
}

class B1 implements Interface1,Interface2 {


    @Override
    public void operation1() {
        System.out.println("B1的operation1");
    }

    @Override
    public void operation2() {
        System.out.println("B1的operation2");
    }

    @Override
    public void operation3() {
        System.out.println("B1的operation3");
    }
}

class C1 {
    public void depend1(Interface1 inf) {
        inf.operation1();
    }

    public void depend4(Interface3 inf) {
        inf.operation4();
    }

    public void depend5(Interface3 inf) {
        inf.operation5();
    }
}

class D1 implements Interface1,Interface3{


    @Override
    public void operation1() {
        System.out.println("D1的operation1");
    }

    @Override
    public void operation4() {
        System.out.println("D1的operation4");
    }

    @Override
    public void operation5() {
        System.out.println("D1的operation5");
    }
}