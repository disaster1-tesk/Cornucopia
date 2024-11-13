package com.disaster.mode.createtype.factorymethod;

public class HNPizzaOrder extends PizzaOrder {
    @Override
    protected Pizza getPizza(String PizzaType) {
        Pizza pizza = null;
        if (PizzaType.equals("ChessPizza")){
            pizza = new HNCheesePizza();
        }else if (PizzaType.equals("GreekPizza")){
            pizza = new HNGreekPizza();
        }else {
            return null;
        }
        return pizza;
    }
}
