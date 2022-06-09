package com.disaster.datastructure.link.v2;

public class Client {
    public static void main(String[] args) {
        LinkedImp<Integer> integerLinkedImp = new LinkedImp<>();
        integerLinkedImp.add(1);
        integerLinkedImp.add(10);
        System.out.println(integerLinkedImp.get(0));
    }
}
