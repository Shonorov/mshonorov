package com.javamentor.funcinterface;

/**
 * Функциональный интерфейс может содержать только один абстрактный метод.
 */
@FunctionalInterface
public interface SimpleFuncInterface {

    void doWork();

    //функциональные интерфейсы могут содержать дополнительно абстрактные методы, определенные в классе Object
    String toString();
    boolean equals(Object o);
}
