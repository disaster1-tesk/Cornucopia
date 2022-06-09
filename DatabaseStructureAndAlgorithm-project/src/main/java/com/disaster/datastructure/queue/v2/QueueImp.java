package com.disaster.datastructure.queue.v2;

import java.util.Arrays;

/*
用数组实现队列:本次实现是有一定问题的，会相应的造成一定的内存泄露，因为当数据被pop出数组时，被pop出的内存是空闲出来没有被使用，术语叫做“假溢出”
解决方案：
通过每当队尾达到数组的最大长度时，我们便将下一个待插入的元素的插入位置（即队尾）转移至数组首端，若该位置可插入，则插入成功，否则判断队满。

　　可以看出：
　　队空时，rear==front;（rear表示数组尾部，front表示数组头部）
　　队满时，((rear+1)%maxn==front);
　　队空时的条件应该很好理解，这里我们只说说队满时的条件讨论。
　　这里的maxn代表队列的最大长度，但为什么要做((rear+1)%maxn==front)的条件判断呢？
　　首先，取模操作无疑是为了使我们的队列“循环”起来。但是(rear+1)%maxn的目的是为了什么呢？
　　假设为(rear%maxn==front)为判断条件，可知有rear始终位于0~maxn之间，则存在将(rear%maxn==front)转化为(rear==front)的情况。因此，我们可能会将队满的情况判断为队空的情况（反之亦可）。
　　所以，在判断条件中给rear加上一个1是为了在对尾与队首之间隔出一个空位，以区分队满与队空两种情况。
 */
public class QueueImp {
    private int size = 0;
    private int popNum = 0;
    private static int DEFAULT_CAPACITY = 10;
    private static Object[] DEFAULTCAPACITY_EMPTY_ELEMENTDATA = new Object[DEFAULT_CAPACITY];
    transient Object[] elementData;

    public QueueImp() {
        this.elementData = DEFAULTCAPACITY_EMPTY_ELEMENTDATA;
    }

    public QueueImp(int initialCapacity) {
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

    public Object pop() {
        Object o  = null;
        if (popNum <= size){
            o = this.elementData[popNum];
            popNum++;
            return o;
        }
        return o;
    }
    public int size(){
        return size;
    }
    private void checkSize() {
        if (size >= this.elementData.length * 0.75) {
            elementData = Arrays.copyOf(elementData, size * 2);
        }
    }
}
