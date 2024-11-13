package com.disaster.basic.lock;

public class LongAdder {
    public static void main(String[] args) {
        //使用longAdder相比较AtomicInteger、AtomicLong在并发效率更高
        java.util.concurrent.atomic.LongAdder longAdder = new java.util.concurrent.atomic.LongAdder();
        longAdder.add(1);
        // 返回当前的值，内部操作是累加所有 Cell 内部的 value值后再累加 base。
        // 例如下面的代码，由于计算总和时没有对 Cell 数组进行加锁，所以在累加过程中可能有其他线程对 Cell 中的值进行了修改，
        // 也有可能对数组进行了扩容，所以 sum 返回的值并不是非常精确的，其返回值并不是一个调用 sum方法时的原子快照值
        System.out.println(longAdder.sum());
        //重置操作， 如下代码把base置为0， 如果Cell数组有元素，则元素值被重置为 0
        longAdder.reset();
        //sum 的改造版本，如下代码在使用 sum 累加对应的 Cell 值后， 把当前Cell的值重置为0, base重置为0。这样， 当多线程调用该方法时会有问题，
        //比如考虑第一个调用线程清空 Cell 的值，则后一个线程调用时累加的都是 0值
        long l = longAdder.sumThenReset();
        System.out.println("l = " + l);
        System.out.println(longAdder.longValue());
    }
}
