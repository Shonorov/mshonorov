package com.javamentor.singletons;

/**
 * Early Initialization singleton.
 * Static fields and blocks are initialized one after another.
 */
public class EarlyInitSingleton {
    private static final EarlyInitSingleton INSTANCE = new EarlyInitSingleton();
    public static EarlyInitSingleton getInstance() {
        return INSTANCE;
    }
}
