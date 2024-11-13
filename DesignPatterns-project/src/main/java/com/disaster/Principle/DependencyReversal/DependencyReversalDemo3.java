package com.disaster.Principle.DependencyReversal;
/*
依赖倒转实现的三种方式：
    1.通过接口实现
    2.通过构造器实现
    3.通过set方法实现
 */
public class DependencyReversalDemo3 {
    public static void main(String[] args) {
//          new OpenAndClose().open(new TV());
//        new OpenAndClose(new TV()).open();
        OpenAndClose openAndClose = new OpenAndClose();
        openAndClose.set(new TV());
        openAndClose.open();
    }
}

/*
第一种方式：
    通过接口的方式来实现依赖倒转原则
 */
/*
interface IOpenAndClose{
    void open(ITV itv);
}
interface ITV{
    String getInfo();
}
class OpenAndClose implements IOpenAndClose{
    @Override
    public void open(ITV itv) {
        System.out.println(itv.getInfo());
    }
}
class TV implements ITV{
    @Override
    public String getInfo() {
        return "TV返回值";
    }
}*/
/*
通过构造器实现依赖倒转
 */
/*
interface IOpenAndClose{
    void open();
}
interface ITV{
    String getInfo();
}
class OpenAndClose implements IOpenAndClose{
    private ITV itv;
    public OpenAndClose(ITV itv) {
        this.itv = itv;
    }
    @Override
    public void open() {
        System.out.println(itv.getInfo());
    }
}
class TV implements ITV{
    @Override
    public String getInfo() {
        return "TV返回值";
    }
}*/
/*
通过set方法实现依赖倒转
 */
interface IOpenAndClose{
    void set(ITV itv);
    void open();
}

interface ITV{
    String getInfo();
}

class OpenAndClose implements IOpenAndClose{
    private ITV itv;
    @Override
    public void set(ITV itv) {
        this.itv = itv;
    }

    @Override
    public void open() {
        System.out.println(itv.getInfo());
    }
}

class TV implements ITV{

    @Override
    public String getInfo() {
        return "TV返回值";
    }
}