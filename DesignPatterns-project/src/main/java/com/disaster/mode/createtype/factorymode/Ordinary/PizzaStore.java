package com.disaster.mode.createtype.factorymode.Ordinary;
/*
    披萨订餐
    1.披萨的种类有很多（比如GreekPizz、CheesePizz等
    2.披萨的制作有prepare，bake，cut，box
    3.完成披萨店订购功能
 */
public class PizzaStore {
    public static void main(String[] args) {
        PizzaOrder pizzaOrder = new PizzaOrder();
        pizzaOrder.sellPizza();
    }
}
