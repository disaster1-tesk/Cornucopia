package com.disaster.mode.createtype.factorymethod;

public class BJPizzaOrder extends PizzaOrder{
    @Override
    protected Pizza getPizza(String PizzaType) {
        Pizza pizza = null;
        if (PizzaType.equals("ChessPizza")){
            pizza = new BJCheesePizza();
        }else if (PizzaType.equals("GreekPizza")){
            pizza = new BJGreekPizza();
        }else {
            return null;
        }
        return pizza;
    }
}
