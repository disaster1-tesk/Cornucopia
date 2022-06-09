package com.disaster.datastructure.hashtable.v1;


import java.util.HashMap;
import java.util.Map;
/*
散列表是一种非线性数据结构，通过利用 Hash 函数将指定的「键 key」映射至对应的「值 value」，以实现高效的元素查找
 */
public class Client {
    public static void main(String[] args) {
// 初始化散列表
        Map<String, Integer> dic = new HashMap<>();

// 添加 key -> value 键值对
        dic.put("小力", 10001);
        dic.put("小特", 10002);
        dic.put("小扣", 10003);

// 从姓名查找学号
        dic.get("小力"); // -> 10001
        dic.get("小特"); // -> 10002
        dic.get("小扣"); // -> 10003



    }
}
