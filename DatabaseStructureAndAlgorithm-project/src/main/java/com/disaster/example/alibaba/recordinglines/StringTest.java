package com.disaster.example.alibaba.recordinglines;

import java.util.*;
import java.util.concurrent.LinkedBlockingDeque;

/**
 *
 */
public class StringTest {

    public static void main(String... args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入字符串：");
        String str = sc.next();
        System.out.println("请输入出现次数：");
        Integer num = sc.nextInt();
        Character firstRepeatChar = findFirstRepeatChar(str, num);
        if (Objects.nonNull(firstRepeatChar)) {
            System.out.println("第一次出现" + num + "次数的字母为:" + firstRepeatChar);
        }
    }

    public static Character findFirstRepeatChar(String str, int num) {
        char[] chars = str.toCharArray();
        HashMap<Character, Integer> result = new HashMap<>();
        HashMap<Character, Integer> finalResult = new LinkedHashMap<>();
        for (int i = 0; i < chars.length; i++) {
            if (result.isEmpty()) {
                if (chars[i] >= 'a' && chars[i] <= 'z' || chars[i] >= 'A' && chars[i] <= 'Z') {
                    result.put(chars[i], 1);
                }
            } else {
                if (chars[i] >= 'a' && chars[i] <= 'z' || chars[i] >= 'A' && chars[i] <= 'Z') {
                    if (result.containsKey(chars[i])) {
                        Integer integer = result.get(chars[i]);
                        integer++;
                        if (integer >= num) {
                            finalResult.put(chars[i], integer);
                        }
                        result.put(chars[i], integer);
                    } else {
                        result.put(chars[i], 1);
                    }
                }
            }

        }
        ArrayList<Character> resultList = new ArrayList<>();
        for (Map.Entry<Character, Integer> entry : finalResult.entrySet()) {
            if (entry.getValue() == num) {
                resultList.add(entry.getKey());
            }
        }
        if (!resultList.isEmpty()) {
            return resultList.get(resultList.size()-1);
        }
        System.out.println("没有只重复" + num + "次的字符");
        return null;
    }

}

