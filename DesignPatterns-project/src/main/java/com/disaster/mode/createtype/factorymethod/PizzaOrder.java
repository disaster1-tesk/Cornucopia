package com.disaster.mode.createtype.factorymethod;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public abstract class PizzaOrder {
    protected PizzaOrder(){
        System.out.println("=============欢迎光临Disaster的Pizza店=====================");
        System.out.println("请输入你想买的Pizza种类：");
        while(true){
            Pizza pizza = getPizza(getPizzaType());
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
    /*public void sellPizza() {
        System.out.println("=============欢迎光临Disaster的Pizza店=====================");
        System.out.println("请输入你想买的Pizza种类：");
        while(true){
            Pizza pizza = getPizza(getPizzaType());
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

    }*/
    protected abstract Pizza getPizza(String PizzaType);
    private String getPizzaType() {
        String stringBuilder = "";
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
