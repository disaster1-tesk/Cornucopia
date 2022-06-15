package com.disaster.High;

import java.util.concurrent.ConcurrentHashMap;

public class HighDemo10 {
    public static void main(String[] args) {
        ConcurrentHashMap<String,Integer> hashMap = new ConcurrentHashMap<>();
        hashMap.put("1",1);
        System.out.println(hashMap.get("1"));
    }
}
