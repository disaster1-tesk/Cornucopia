package com.disaster.jvm.Classloader;
/*
引导类加载器：
底层是由c++写的，目的是加载jdk中的核心jar包，加载扩展类和应用程序类加载器，并指定为他们的负累加载器
同时处于安全考虑BootStrap启动类加载器只加载包名为javax、java、sun等开头的类。

扩展类加载器：
底层由java语言编写，目的是加载java.ext.dirs系统属性所指定的目录中加载类库，或从jdk的安装目录的jer/lib/ext子目录下加载类库。
如果用户创建的jar放在此目录下，也会自动由扩展类加载器加载。

应用类加载器：
底层由java编写，目的是加载环境变量classpath或系统属性java.class.path指定路径下的类库。
该类加载是程序中默认的类加载器，一般来说，java应用的类都是由它来完成加载，通过ClassLoader.getSystemCLssLoader方法可以获取到该类加载器

自定义类加载器：
继承ClassLoader并重写findClass方法
 */
public class ClassloaderDemo2 {
    public static void main(String[] args) {
        ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
        System.out.println(systemClassLoader);//jdk.internal.loader.ClassLoaders$AppClassLoader@3fee733d

        ClassLoader parent = systemClassLoader.getParent();
        System.out.println(parent);//jdk.internal.loader.ClassLoaders$PlatformClassLoader@10f87f48

        ClassLoader parent1 = parent.getParent();
        System.out.println(parent1);//System.out.println(parent1);

        ClassLoader classLoader = String.class.getClassLoader();
        System.out.println(classLoader);//null

    }
}
class myClassLoader extends ClassLoader{
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        return super.findClass(name);
    }
}
