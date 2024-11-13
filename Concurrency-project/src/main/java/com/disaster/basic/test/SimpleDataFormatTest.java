package com.disaster.basic.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 *每个SimpleDateFormat实例里面都有一个 Calendar对象，  SimpleDateFormat 之所以是线程不安全的， 就是因为 Calendar 是线程不安全的
 *Calendar之所以是线程不安全的，是因为其中存放日期数据的变量都是线程不安全的，比如 fields、 time 等
 */
public class SimpleDataFormatTest {
    public static void main(String[] args) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for (int i = 0; i < 100000; i++) {
            new Thread(()->{
                try {
                    System.out.println(sdf.parse("2023-02-12 19:10:50"));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
