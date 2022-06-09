package com.disaster;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;

public class App {
    public static void main(String[] args) {
        URLClassLoader contextClassLoader = (URLClassLoader) Thread.currentThread().getContextClassLoader();
        URL[] urLs = contextClassLoader.getURLs();
        for (URL urL : urLs) {
            System.out.println("urL = " + urL);
        }
        System.out.println("===================================================");
        final String var1 = System.getProperty("java.class.path");
        File[] classPath = App.getClassPath(var1);
        for (File file : classPath) {
            System.out.println("file = " + file);
        }
        System.out.println("============================");
        final File[] var2 = var1 == null ? new File[0] : App.getClassPath(var1);
        for (File file : var2) {
            System.out.println("file = " + file);
        }
    }
    private static File[] getClassPath(String var0) {
        File[] var1;
        if (var0 != null) {
            int var2 = 0;
            int var3 = 1;
            boolean var4 = false;

            int var5;
            int var7;
            for(var5 = 0; (var7 = var0.indexOf(File.pathSeparator, var5)) != -1; var5 = var7 + 1) {
                ++var3;
            }

            var1 = new File[var3];
            var4 = false;

            for(var5 = 0; (var7 = var0.indexOf(File.pathSeparator, var5)) != -1; var5 = var7 + 1) {
                if (var7 - var5 > 0) {
                    var1[var2++] = new File(var0.substring(var5, var7));
                } else {
                    var1[var2++] = new File(".");
                }
            }

            if (var5 < var0.length()) {
                var1[var2++] = new File(var0.substring(var5));
            } else {
                var1[var2++] = new File(".");
            }

            if (var2 != var3) {
                File[] var6 = new File[var2];
                System.arraycopy(var1, 0, var6, 0, var2);
                var1 = var6;
            }
        } else {
            var1 = new File[0];
        }

        return var1;
    }
}
