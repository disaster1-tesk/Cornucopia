package com.disaster.happens_before;

import java.util.concurrent.TimeUnit;

public class Demo2 {
    private Integer age;

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public static void main(String[] args) {
        Demo2 demo2 = new Demo2();
        new Thread(() -> {
            //TODO
            demo2.setAge(1);
        }, "A").start();
        new Thread(() -> {
            //TODO
            System.out.println(demo2.getAge());
        }, "B").start();
    }
}
