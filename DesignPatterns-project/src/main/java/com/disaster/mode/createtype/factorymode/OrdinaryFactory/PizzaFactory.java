package com.disaster.mode.createtype.factorymode.OrdinaryFactory;

public class PizzaFactory {
    String PizzaType;
    Pizza pizza;
    public Pizza getPizza(String PizzaType){
        if (PizzaType.equals("CheesePizza")){
            pizza = new CheesePizza();
            return pizza;
        }else if (PizzaType.equals("GreekPizza")){
            pizza = new GreekPizza();
            return pizza;
        }else if (PizzaType.equals("HotPizza")){
            pizza = new HotPizza();
            return pizza;
        } else{
            return null;
        }
    }
}
