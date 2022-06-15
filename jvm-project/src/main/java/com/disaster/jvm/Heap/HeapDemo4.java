package com.disaster.jvm.Heap;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
/*

新生代中的结构包括：Eden（伊甸区），两个Survive区，且他们的比例为6:1:1
而hotspot中默认的堆中新生代与老年代内存的比例为1:2

设置新生代与老年代在堆结构中的占比：
    -XX：NewRatio=2  表示新生代为1，老年代为2，新生代占总堆内存的1/3
    -xx:NewRatio=3   表示新生代为1,老年代为3，新生代占总堆内存的1/4

一般在开发中不需要更改堆中内存的比例分配，但是如果说一些老年区的数据比较多的时候，我们要适当的将老年代的比例设置的更大一些！

在hotspot中，Eden空间和另外一个Survivor空间缺省所占的比例是8:1:1
当然开发人员可以通过选项“-XX：SurvivorRatio”调整这个空间比例。比如：
-XX：SurvivorRatio=8
“几乎”所有的对象都是在伊甸区创建的，但是如果创建的对象大于伊甸区的内存大小
那么就会有后续的一些操作！


-XX:NewRatio:设置新生代与老年代的比例，默认值是2
-XX:SurvivorRatio:设置新生代中Eden区与Survivor区的比例
-XX:-UseAdaptiveSizePolicy:关闭自适应的内存分配策略
-Xmn:设置新生代的空间的大小
 */
public class HeapDemo4 {
    public static void main(String[] args) {
        List<test> list = new ArrayList<test>();
        while (true){
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            list.add(new test(new Random().nextInt(1024*100)));
        }
    }
}
class test{
    private byte[] bytes;

    public test(int length) {
        this.bytes = new byte[length];
    }
}