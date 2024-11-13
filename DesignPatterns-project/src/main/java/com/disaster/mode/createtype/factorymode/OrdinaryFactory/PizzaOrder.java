package com.disaster.mode.createtype.factorymode.OrdinaryFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PizzaOrder {
    public void sellPizza() {
        System.out.println("=============欢迎光临Disaster的Pizza店=====================");
        System.out.println("请输入你想买的Pizza种类：");
        while(true){
            PizzaFactory pizzaFactory = new PizzaFactory();
            Pizza pizza = pizzaFactory.getPizza(getPizzaType());
            if (pizza!=null){
                pizza.prepare();
                pizza.bake();
                pizza.cut();
                pizza.box();
                System.out.println("请在输入你想买的其他Pizza：");
            }else{
                System.out.println("本店没有此披萨");
                return;
            }
        }

    }

    private String getPizzaType() {
        String stringBuilder;
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        try {
            if ((stringBuilder = bf.readLine()) != null) {
                return stringBuilder;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
