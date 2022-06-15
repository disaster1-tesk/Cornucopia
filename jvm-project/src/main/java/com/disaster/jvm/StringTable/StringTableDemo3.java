package com.disaster.jvm.StringTable;

import org.junit.Test;

/*
判断字符串的相关操作过程中创建的对象个数
 */

public class StringTableDemo3 {
    public static void main(String[] args) {

    }
    @Test
    public void test1(){
//        创建str时总共创建了两个对象，一个堆中的String对象和字符串常量池中的String对象
        String str = new String("123");
//         创建s时总共创建了6个对象，两个new对象分别在堆中和字符串常量池中各创建了两个对象，由于这两个new对象是引用对象，
//         所以在进行字符串的拼接时会创建一个StringBuilder类来进行字符串的拼接，同时调用toString方法的时候创建了一个String对象，
//         但是这里使用toString方法不会再字符串常量池中创建一个String对象。这就是和new对象的区别
        String s = new String("12")+new String("3");
        System.out.println(str==s);
    }
    @Test
    public void test2(){
//        str指的是堆中创建的的数据，s指的是字符串常量池中的对象，两者的地址不同，所以结果会是false
        String str = new String("abc");
        str.intern();
        String s = "abc";
        System.out.println(str == s);
    }
    /*
    总结String的intern（）的使用：
        jdk1.6中，将这个字符串对象尝试放入串池。
            如果串池中有，则不会放入。返回已有的串池中的对象的地址
            如果没有，会把对象赋值一份，放入串池，并返回串池中的对象地址
        jdk1.8中，将这个字符串尝试放入串池。
            如果串池中有，则并不会放入。返回已有的串池中的对象的地址
            如果没有，则会把对象的引用地址复制一份，放入串池，并返回串池中的引用地址
     */
    @Test
    public void test3(){
        String str =new String("ab")+new String("c");
        str.intern();//判断字符串常量池中是否有此字符串，如果没有，则在字符串常量池中创建一个字符串，并返回这个字符串对象地址
        String s = "abc";
        System.out.println(str == s);//结果 在JDK1.6中，是false。在JDK1.8中，是true(对于不同版本jdk结果不同的解释：因为在jdk1.6中字符串常量池是放在永久代中，在str.intern方法执行完后，会创建创建一个新的对象
//        但是对于 jdk1.8之后，由于字符串常量池是放入到了堆中，在str.intern之后，字符串常量池中的对象指的也是堆中创建的String对象)
    }
}
