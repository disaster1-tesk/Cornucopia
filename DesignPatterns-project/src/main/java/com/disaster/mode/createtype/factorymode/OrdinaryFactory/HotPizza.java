package com.disaster.mode.createtype.factorymode.OrdinaryFactory;

public class HotPizza extends Pizza {
    private String PizzaType;
    public HotPizza(){
        this.PizzaType = "HotPizza";
    }
    @Override
    protected void prepare() {
        System.out.println(PizzaType+"材料正在准备完毕");
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
