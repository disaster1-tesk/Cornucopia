package com.disaster.datastructure.link.v1;

import java.util.LinkedList;

/**
 * @ClassName Client
 * @Description TODO
 * @Author Disaster
 * @Date 2021/7/7 20:35
 * @Version 1.0
 **/
public class Client {
    public static void main(String[] args) {
        LinkedList<Integer> objects = new LinkedList<>();
        ListNode l1 = new ListNode(1);
        ListNode l5 = new ListNode(5);
        ListNode l3 = new ListNode(3);
        l1.next = l5;
        l5.next = l3;
        System.out.println(l1.getNext());
        System.out.println(l5.next);
    }
}

class ListNode {
    private int val;
    ListNode next;

    public ListNode(int val) {
        this.val = val;
    }
    public ListNode getNext(){
        return next;
    }

    @Override
    public String toString() {
        return "ListNode{" +
                "val=" + val +
                ", next=" + next +
                '}';
    }
}