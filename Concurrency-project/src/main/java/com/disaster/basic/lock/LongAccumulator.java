package com.disaster.basic.lock;

public class LongAccumulator {
    public static void main(String[] args) {
        //LongAdder是LongAccumulator的一种特殊场景
        //LongAccumulator与LongAdder的主要区别在于LongAccumulator提供了双目计算接口去控制计算逻辑、而LongAdder是采用默认相加的计算逻辑
        java.util.concurrent.atomic.LongAccumulator longAccumulator = new java.util.concurrent.atomic.LongAccumulator((left, right) -> left * right, 1l);
        longAccumulator.accumulate(3);
        longAccumulator.accumulate(4);
        System.out.println(longAccumulator.longValue());
    }
}
