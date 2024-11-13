package com.disaster.cglib;

import net.sf.cglib.proxy.Mixin;

public class MixinCreator {
    public static void main(String[] args) {
        Mixin mixin = Mixin.create(
                new Class[]{ Interface1.class, Interface2.class, MixinInterface.class },
                new Object[]{ new Class1(), new Class2() }
        );
        MixinInterface mixinDelegate = (MixinInterface) mixin;
        String first = mixinDelegate.first();
        System.out.println("first = " + first);
        String second = mixinDelegate.second();
        System.out.println("second = " + second);
    }

    public interface MixinInterface extends Interface1, Interface2 { }

    public interface Interface1 {
        String first();
    }

    public interface Interface2 {
        String second();
    }

    public static class Class1 implements Interface1 {
        @Override
        public String first() {
            return "first behaviour";
        }
    }

    public static class Class2 implements Interface2 {
        @Override
        public String second() {
            return "second behaviour";
        }
    }

}
