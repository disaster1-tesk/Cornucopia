package com.disaster.jvm.Classloader;

/*
                                双亲委派机制
 1.在new对象的时候，如果new的对象的加载器有父类，那就会把这个类的加载交给父类去做，依次类推，如果父类加载的目录中有这个类，那么这个类的加载就由父类加载器完成，
 如果完成不了，那就交给子类去完成，依次类推。
 2.在正常的程序执行中，双亲委派机制的执行流程一般是：
 DefineClassLoader------------>ApplicationClassLoader-------->ExtensionCLssLoader-------->BootStrapClassLoader

优点：
    1.避免类的重复加载
    2.提高类加载的安全性，保护程序安全，防止核 心API被篡改
 */
public class ClassLoaderDemo5 {
    public static void main(String[] args) {
        String s = new String();
        System.out.println(s);
    }
}
