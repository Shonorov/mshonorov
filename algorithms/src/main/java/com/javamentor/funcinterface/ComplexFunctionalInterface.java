package com.javamentor.funcinterface;

@FunctionalInterface
public interface ComplexFunctionalInterface extends SimpleFuncInterface {

    //Интерфейс может иметь методы с реализацией, помеченные как default
    default void doSomeWork() {
        System.out.println("Doing some work in interface impl...");
    }

    //Интерфейс может иметь методы с реализацией, помеченные как default
    default void doSomeOtherWork() {
        System.out.println("Doing some other work in interface impl...");
    }
}
