package com.javamentor.singletons;

/**
 * Double-checked locking singleton.
 * Volatile not supported in Java < 1.4
 * Hard to read.
 */
public class DclSingleton {

    private static volatile DclSingleton instance;
    public static DclSingleton getInstance() {
        if (instance == null) {
            synchronized (DclSingleton.class) {
                if (instance == null) {
                    instance = new DclSingleton();
                }
            }
        }
        return instance;
    }
}
