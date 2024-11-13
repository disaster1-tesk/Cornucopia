package com.disaster.sugar.generics;


import org.testng.collections.Lists;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Generics {
    public static void main(String[] args) {
        demo(Lists.newArrayList(new User("disaster", 1), new User("disaster1", 2)));
        demo1(Lists.newArrayList(new User("disaster", 1), new User("disaster1", 2), "disaster"));
        demo2();
    }

    public static void demo(List<User> users) {
        for (User user : users) {
            System.out.println(user.getName());
        }
    }

    public static void demo1(List users) {
        for (Object user : users) {
            User user1 = (User) user;
            System.out.println(user1.getName());
        }
    }

    public static void demo2() {
        Map<String, String> map = new HashMap();
        map.put("hello", "你好");
        map.put("how are you?", "吃了没?");
        System.out.println(map.get("hello"));
        System.out.println(map.get("how are you?"));
    }

    public static void demo3(Integer... args) {
        List<Integer> list = Arrays.asList(args);
        Integer sum = 0;
        for (Integer i : list) {
            sum += i;
            System.out.println(i + sum);
        }
        System.out.println(sum);
    }

    public static void demo4() {
        int i = 0;
        Integer l = 2;
        System.out.println(i + l);
    }

    public static void demo5() {
        if (false) {
            System.out.println("block 1");
        } else {
            System.out.println("block 2");
        }
    }

    private static class User {
        private String name;
        private Integer age;

        public User(String name, Integer age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }

        @Override
        public String toString() {
            return "User{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }
    }
}
