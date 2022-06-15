package com.disaster.OOM;

import java.util.ArrayList;
import java.util.List;

/*
java中内存泄露的8中情况：
    1.静态集合类
 */
public class OOMDemo1 {
    private List<Object> objectList = new ArrayList<>();
    public void addObject(Object o){
        objectList.add(o);
    }
    public static void main(String[] args) {

    }
}
