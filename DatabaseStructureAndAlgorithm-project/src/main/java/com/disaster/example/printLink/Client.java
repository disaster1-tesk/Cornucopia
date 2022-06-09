package com.disaster.example.printLink;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Stack;

/*
输入一个链表的头节点，从尾到头反过来返回每个节点的值（用数组返回）。
输入：head = [1,3,2]
输出：[2,3,1]
 */
public class Client {
    public static void main(String[] args) {
        ListNode listNode = new ListNode(1);
        ListNode listNode1 = new ListNode(2);
        ListNode listNode2 = new ListNode(3);
        listNode.next = listNode1;
        listNode1.next = listNode2;
        int[] ints = reversePrint(listNode);
        int[] integers = reversePrint1(listNode);
        for (int i = 0; i < ints.length; i++) {
            System.out.println(ints[i]);
        }
        for (Integer integer : integers) {
            System.out.println(integer);
        }
    }
    public static int[] reversePrint1(ListNode head){
        Stack<Integer> stack = new Stack<>();
        ListNode temp = head;
        while (temp!=null){
            stack.push(temp.val);
            temp = temp.next;
        }
        int[] ints = new int[stack.size()];
        for (int i = 0; i < ints.length; i++) {
            ints[i] = stack.pop();
        }
        return ints;
    }
    public static int[] reversePrint(ListNode head) {
        LinkedList<ListNode> stack = new LinkedList<>();
        ListNode temp = head;
        while (temp != null) {
            stack.add(temp);
            temp = temp.next;
        }
        int size = stack.size();
        int[] print = new int[size];
        for (int i = 0; i < size; i++) {
            print[i] = stack.getLast().val;
            stack.removeLast();
        }
        return print;
    }
}
class ListNode {
     int val;
     ListNode next;
     ListNode(int x) { val = x; }
 }
