package com.disaster.mode.createtype.factorymode.Ordinary;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PizzaOrder {
    public void sellPizza() {
        System.out.println("=============欢迎光临Disaster的Pizza店=====================");
        System.out.println("请输入你想买的Pizza种类：");
        while(true){
            String pizzaType = getPizzaType();
            Pizza pizza;
            if (pizzaType.equals("CheesePizza")){
                pizza = new CheesePizza();
            }else if (pizzaType.equals("GreekPizza")){
                pizza = new GreekPizza();
            }else{
                System.out.println("本店没有此Pizza");
                return;
            }
            if (pizza instanceof CheesePizza) {
                pizza.prepare();
                pizza.bake();
                pizza.cut();
                pizza.box();
                System.out.println("CheesePizza" + "卖出完毕...");
            } else if (pizza instanceof GreekPizza) {
                pizza.prepare();
                pizza.bake();
                pizza.cut();
                pizza.box();
                System.out.println("GreekPizza" + "卖出完毕...");
            }
            System.out.println("请在输入你想买的其他Pizza：");
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
