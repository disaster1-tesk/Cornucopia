package com.disaster.mode.createtype.absfactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class PizzaOrder {
    public void sellPizza() {
        System.out.println("=============欢迎光临Disaster的Pizza店=====================");
        System.out.println("您想买什么地区披萨：");
        PizzaFactory factory = createFactory();
        if (factory!=null){
            System.out.println("选择完毕，请您输入想吃的披萨种类：");
        }
        while (true) {
            if (factory==null){
                break;
            }
            Pizza pizza = factory.createPizza(getPizzaType());
            if (pizza != null) {
                pizza.prepare();
                pizza.bake();
                pizza.cut();
                pizza.box();
                System.out.println("请在输入你想买的其他Pizza：");
            } else {
                System.out.println("本店没有此披萨");
                return;
            }
        }

    }

    private PizzaFactory createFactory() {
        PizzaFactory pizzaFactory = null;
        Scanner scanner = new Scanner(System.in);
        String next = scanner.next();
        if (next.equals("BJ")) {
            pizzaFactory = new BJPizzaFactory();
        } else if (next.equals("HN")) {
            pizzaFactory = new HNPizzaFactory();
        } else {
            System.out.println("没有此地区的披萨");
            return null;
        }
        return pizzaFactory;
    }

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
