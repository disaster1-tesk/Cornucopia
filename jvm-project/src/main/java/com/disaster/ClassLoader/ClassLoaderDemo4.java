package com.disaster.ClassLoader;

import sun.misc.Launcher;

import java.net.URL;

public class ClassLoaderDemo4 {
    public static void main(String[] args) {
        //得到bootstrapclassloader的URL路径
        URL[] urLs = Launcher.getBootstrapClassPath().getURLs();
        System.out.println(Launcher.getLauncher().getClassLoader());
        System.out.println(Launcher.getLauncher().getClassLoader().getParent());
        System.out.println(Launcher.getLauncher().getClassLoader().getParent().getResource(""));
        for (URL url:
             urLs) {
            System.out.println(url);
        }
    }
}
