package com.disaster.mode.behavior.stategy.Arrays;

import java.util.Arrays;

/**
 * @ClassName Client
 * @Description TODO
 * @Author Disaster
 * @Date 2021/7/3 12:28
 * @Version 1.0
 **/
public class Client {
    public static void main(String[] args) {
        //Arrays中的sort方法就是用的策略模式
        Integer[] integers = {1,4,6,8,9,0,10};
        Arrays.sort(integers,(a1,a2)-> a1-a2);
        for (Integer integer : integers) {
            System.out.println(integer);
        }
    }
}
