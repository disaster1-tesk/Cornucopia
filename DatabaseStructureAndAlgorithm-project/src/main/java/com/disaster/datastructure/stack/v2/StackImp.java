package com.disaster.datastructure.stack.v2;

import java.util.Arrays;

/*
用数组实现栈
 */
public class StackImp {
    private int size = 0;
    private static int DEFAULT_CAPACITY = 10;
    private static Object[] DEFAULTCAPACITY_EMPTY_ELEMENTDATA = new Object[DEFAULT_CAPACITY];
    transient Object[] elementData;

    public StackImp() {
        this.elementData = DEFAULTCAPACITY_EMPTY_ELEMENTDATA;
    }

    public StackImp(int initialCapacity) {
        if (initialCapacity > 0) {
            this.elementData = new Object[initialCapacity];
        } else if (initialCapacity == 0) {
            this.elementData = DEFAULTCAPACITY_EMPTY_ELEMENTDATA;
        } else {
            throw new IllegalArgumentException("Illegal Capacity: " +
                    initialCapacity);
        }
    }

    public void push(Object o) {
        checkSize();
        this.elementData[size] = o;
        size++;
    }
    public int size(){
        return size;
    }
    public Object pop() {
        Object o  = null;
        if (size >0) {
            o = this.elementData[size-1];
            size--;
            return o;
        }else if (size ==0){
            return this.elementData[size];
        }
        return o;
    }

    private void checkSize() {
        if (size >= this.elementData.length * 0.75) {
            elementData = Arrays.copyOf(elementData, size * 2);
        }
    }


}
