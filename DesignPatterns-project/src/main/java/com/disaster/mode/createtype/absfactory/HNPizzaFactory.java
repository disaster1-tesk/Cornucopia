package com.disaster.mode.createtype.absfactory;

public class HNPizzaFactory extends PizzaFactory {
    @Override
    public Pizza createPizza(String PizzaType) {
        Pizza pizza = null;
        if (PizzaType.equals("CheesePizza")){
            pizza = new HNCheesePizza();
        }else if(PizzaType.equals("GreekPizza")){
            pizza = new HNGreekPizza();
        }else {
            return null;
        }
        return pizza;
    }
}
