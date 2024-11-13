package com.disaster.mode.createtype.absfactory;

public class BJCheesePizza extends Pizza {
    String PizzaType;
    public BJCheesePizza(){
        this.PizzaType = "BJCheesePizza";
    }
    @Override
    protected void prepare() {
        this.PizzaType = PizzaType;
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
