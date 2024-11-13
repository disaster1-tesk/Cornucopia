package com.disaster.basic.lock;

import lombok.SneakyThrows;

import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

public class CopyOnWrite {
    public static void main(String[] args) {
//        bashPractice();
        highPractice();
    }

    private static void bashPractice() {
        //CopyOnWriteArrayList所有的设计修改的Api都会获取CopyOnWriteArrayList#lock对象进行加锁，通过这种方式去保证原子性
        //对于获取或者查询相关Api不会通过加锁实现
        CopyOnWriteArrayList<Object> copyOnWriteArrayList = new CopyOnWriteArrayList<>();
        copyOnWriteArrayList.add("hello");
        /**
         * add：获取lock -> 复制旧数组到数组长度+1的新的数组中 -> setArray(newElements);
         */
        copyOnWriteArrayList.add("world");
        /**
         * set: 获取lock -> 获取index下的旧数据 -> 如果旧数据与新数据相同调用setArray(newElements);
         * 否则复制一份新的数组并将旧数据覆盖掉最后调用调用setArray(newElements)
         *
         */
        copyOnWriteArrayList.set(1, "disaster");
        /**
         * get：没有获取lock的操作，直接从数组中获取对应index的值
         */
        System.out.println(copyOnWriteArrayList.get(1));
        /**
         * remove：拿到lock -> 获取index下的元素 -> 如果是最后一个元素则直接Arrays.copyOf(elements, len - 1)，否则通过
         * System.arraycopy(elements, 0, newElements, 0, index);
         * System.arraycopy(elements, index + 1, newElements, index,numMoved);
         * 两个方法去复制数据到新的object数组中 -> setArray(newElements);
         *
         * **/
        copyOnWriteArrayList.remove(1);
        /**
         * iterator：弱一致性，
         */
        Iterator<Object> iterator = copyOnWriteArrayList.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
        System.out.println("copyOnWriteArrayList = " + copyOnWriteArrayList);
    }


    @SneakyThrows
    private static void highPractice(){
        //通过下面案例证明copyOnWriteArrayList的iterator是一个快照数据
        CopyOnWriteArrayList<Object> copyOnWriteArrayList = new CopyOnWriteArrayList<>();
        copyOnWriteArrayList.add("hello");
        copyOnWriteArrayList.add("world");
        copyOnWriteArrayList.add("i'm");
        copyOnWriteArrayList.add("disaster");
        copyOnWriteArrayList.add("haha");
        Thread thread1 = new Thread(() -> {
            copyOnWriteArrayList.remove(4);
            copyOnWriteArrayList.set(3,"disaster1");
        }, "thread1");
        Iterator<Object> iterator = copyOnWriteArrayList.iterator();
        thread1.start();
        thread1.join();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }
}
