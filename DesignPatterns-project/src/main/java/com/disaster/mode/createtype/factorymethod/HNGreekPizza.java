package com.disaster.mode.createtype.factorymethod;

public class HNGreekPizza extends Pizza {
    String PizzaType;
    public HNGreekPizza(){
        this.PizzaType = "HNGreekPizza";
    }
    @Override
    protected void prepare() {
        System.out.println(PizzaType+"材料准备完毕");
    }

    @Override
    protected void bake() {
        System.out.println(PizzaType+"正在烘烤");
    }

    @Override
    protected void cut() {
        System.out.println(PizzaType+"正在切片");
    }

    @Override
    protected void box() {
        System.out.println(PizzaType+"打包完毕");
    }
}
