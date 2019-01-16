package com.javamentor.singletons;

/**
 * Initialization on Demand (lazy) singleton.
 * Class initialization occurs the first time we use one of its methods or fields.
 * The InstanceHolder class will assign the field the first time we access it by invoking getInstance.
 */
public class InitOnDemandSingleton {

    private static class InstanceHolder {
        private static final InitOnDemandSingleton INSTANCE = new InitOnDemandSingleton();
    }
    public static InitOnDemandSingleton getInstance() {
        return InstanceHolder.INSTANCE;
    }
}
