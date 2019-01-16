package com.javamentor.singletons;

/**
 * Simple draconian thread-safe singleton.
 * Each time we want to get the instance of our singleton, we need to acquire a potentially unnecessary lock.
 */
public class SimpleSingleton {

    private static SimpleSingleton instance;
    public static synchronized SimpleSingleton getInstance() {
        if (instance == null) {
            instance = new SimpleSingleton();
        }
        return instance;
    }
}
