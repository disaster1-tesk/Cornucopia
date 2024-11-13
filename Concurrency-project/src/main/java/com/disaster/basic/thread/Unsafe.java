package com.disaster.basic.thread;

import lombok.SneakyThrows;

import java.lang.reflect.Field;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/**
 * The type Unsafe.
 */
public class Unsafe {
    private static sun.misc.Unsafe unsafe;

    static {
        unsafe = getUnsafe();
    }

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    @SneakyThrows
    public static void main(String[] args) {
        objectFieldOffset();
        arrayBaseOffset();
        arrayIndexScale();
        compareAndSwapLong();
        getLongVolatile();
        putLongVolatile();
        putOrderedLong();
        getAndSetLong();
        getAndAddLong();
        park();
    }

    private static void objectFieldOffset() throws NoSuchFieldException {
        //返回指定的变量在所属类中的内存偏移地址，该偏移地址仅仅在该Unsafe函数中访问指定字段时使用。
        long value = unsafe.objectFieldOffset(AtomicLong.class.getDeclaredField("value"));
        System.out.println("value = " + value);
    }

    private static void arrayBaseOffset() throws NoSuchFieldException {
        String[] strings = new String[]{"123", "1234"};
        //获取数组中第一个元素的地址
        int i = unsafe.arrayBaseOffset(strings.getClass());
        System.out.println("i = " + i);
    }

    private static void arrayIndexScale() throws NoSuchFieldException {
        String[] strings = new String[]{"12", "123", "1234"};
        //获取数组中第一个元素占用的字节
        int i = unsafe.arrayIndexScale(strings.getClass());
        System.out.println("i = " + i);
    }

    private static void compareAndSwapLong() throws NoSuchFieldException {
        AtomicLong atomicLong = new AtomicLong(1l);
        long valueOffset = unsafe.objectFieldOffset
                (AtomicLong.class.getDeclaredField("value"));
        boolean b = unsafe.compareAndSwapLong(atomicLong, valueOffset, 1l, 2l);
        System.out.println("b = " + b);
        System.out.println("atomicLong = " + atomicLong.get());
    }

    private static void getLongVolatile() throws NoSuchFieldException {
        AtomicLong atomicLong = new AtomicLong(1l);
        long valueOffset = unsafe.objectFieldOffset
                (AtomicLong.class.getDeclaredField("value"));
        //获取对象 obj 中偏移量为 offset 的变量对应 volatile 语义的值 。
        long longVolatile = unsafe.getLongVolatile(atomicLong, valueOffset);
        System.out.println("longVolatile = " + longVolatile);
    }

    private static void putLongVolatile() throws NoSuchFieldException {
        AtomicLong atomicLong = new AtomicLong(1l);
        long valueOffset = unsafe.objectFieldOffset
                (AtomicLong.class.getDeclaredField("value"));
        //设置 obj 对象中 offset 偏移的类型为 long的 field的值为 value，支持 volatile语义。
        unsafe.putLongVolatile(atomicLong, valueOffset, 2l);
        System.out.println("atomicLong = " + atomicLong.get());
    }

    private static void putOrderedLong() throws NoSuchFieldException {
        AtomicLong atomicLong = new AtomicLong(1l);
        long valueOffset = unsafe.objectFieldOffset
                (AtomicLong.class.getDeclaredField("value"));
        // 设置 obj 对象 中 offset 偏移地址对应的 long型 field 的值为 value。
        // 这是一个有延迟的 putLongvolatile方法， 并且不保证值修改对其他线程立刻可见。 只有在变量使用 volatile修饰并且预计会 被意外修改时才使用该方法
        unsafe.putOrderedLong(atomicLong, valueOffset, 2l);
        System.out.println("atomicLong = " + atomicLong.get());
    }

    private static void getAndSetLong() throws NoSuchFieldException {
        AtomicLong atomicLong = new AtomicLong(1l);
        long valueOffset = unsafe.objectFieldOffset
                (AtomicLong.class.getDeclaredField("value"));
        // 获取对象 obj 中偏移 量为 offset的变量 volatilei吾义的当前值， 并设置变量 volatilei吾义的值为 update
        long andSetLong = unsafe.getAndSetLong(atomicLong, valueOffset, 2l);
        System.out.println("andSetLong = " + andSetLong);
        System.out.println("atomicLong = " + atomicLong.get());
    }

    private static void getAndAddLong() throws NoSuchFieldException {
        AtomicLong atomicLong = new AtomicLong(1l);
        long valueOffset = unsafe.objectFieldOffset
                (AtomicLong.class.getDeclaredField("value"));
        // 获取对象 。同 中偏移 量为 offset的变量 volatilei吾义的当前值， 并设置变量值为原始值+addValue
        long andSetLong = unsafe.getAndAddLong(atomicLong, valueOffset, 2l);
        System.out.println("andSetLong = " + andSetLong);
        System.out.println("atomicLong = " + atomicLong.get());
    }


    @SneakyThrows
    private static void park() throws NoSuchFieldException {
        //阻塞当前线程 ， 其中参数 isAbsolute 等于 false且 time等于 0表示一直阻塞。
        // time大于 0表示等待指定的 time后阻塞线 程会被唤醒， 这个 time是个相对值， 是个增量值， 也就是相对当前时间累加 time 后当前线程就会被唤醒。
        // 如果 isAbsolute等于 true， 并且 time大于 0，则表示阻塞 的线程到指定的时间点后会被唤醒，这里 time是个绝对时间， 是将某个时间点换 算为 ms后的值。
        // 另外，当其他线程调用了当前阻塞线程的 intem1pt方法而中断了 当前线程时-， 当前线程也会返回， 而当其他线程调用 了 unPark方法并且把当前线 程作为 参数 时 当 前 线程 也 会返 回
        Thread thread1 = new Thread(() -> {
            long s = System.currentTimeMillis();
            System.out.println(Thread.currentThread().getName() + " is park");
            unsafe.park(false, 0);
            long e = System.currentTimeMillis();
            System.out.println(Thread.currentThread().getName() + " is notify , time = " + (e-s));
        }, "thread1");
        Thread thread2 = new Thread(() -> {
            long s = System.currentTimeMillis();
            System.out.println(Thread.currentThread().getName() + " is park");
            unsafe.park(false,10);
            long e = System.currentTimeMillis();
            System.out.println(Thread.currentThread().getName() + " is notify , time = " + (e-s));
        }, "thread2");
        Thread thread3 = new Thread(() -> {
            long s = System.currentTimeMillis();
            System.out.println(Thread.currentThread().getName() + " is park");
            unsafe.park(true,10);
            long e = System.currentTimeMillis();
            System.out.println(Thread.currentThread().getName() + " is notify , time = " + (e-s));
        }, "thread3");
        Thread thread4 = new Thread(() -> {
            long s = System.currentTimeMillis();
            System.out.println(Thread.currentThread().getName() + " is park");
            unsafe.park(true,0);
            long e = System.currentTimeMillis();
            System.out.println(Thread.currentThread().getName() + " is notify , time = " + (e-s));
        }, "thread4");

        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        TimeUnit.SECONDS.sleep(10);
        unsafe.unpark(thread1);
    }


    /**
     * Gets unsafe.
     *
     * @return the unsafe
     */
    public static sun.misc.Unsafe getUnsafe() {
        try {
            Field field = sun.misc.Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            return (sun.misc.Unsafe) field.get(null);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }
}
