package com.disaster.Volatile;

public class VolatileTest2 {
    private static VolatileTest2 singletonInstance;

    public static VolatileTest2 getVolatileTest2() {
        if (singletonInstance == null) {
            synchronized (VolatileTest2.class) {
                if (singletonInstance == null) {
                    singletonInstance = new VolatileTest2();
                }
            }
        }
        return singletonInstance;
    }
}
