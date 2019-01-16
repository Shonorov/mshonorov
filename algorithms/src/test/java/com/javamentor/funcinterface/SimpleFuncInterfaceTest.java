package com.javamentor.funcinterface;

import org.junit.Test;

public class SimpleFuncInterfaceTest {

    @Test
    public void whenFunctionalInterfaceThenLambda() {
        carryOutWork(new SimpleFuncInterface() {
            @Override
            public void doWork() {
                System.out.println("Do work in SimpleFun impl...");
            }
        });
        carryOutWork(() -> System.out.println("Do work in lambda exp impl..."));
    }

    public static void carryOutWork(SimpleFuncInterface sfi) {
        sfi.doWork();
    }

    @Test
    public void whenFunctionalInterfaceThenDefaultMethods() {
        ComplexFunctionalInterface functionalInterface = new ComplexFunctionalInterface() {
            @Override
            public void doWork() {
                System.out.println("Do work in ComplexFun impl...");
            }
        };
        functionalInterface.doWork();
        functionalInterface.doSomeWork();
        functionalInterface.doSomeOtherWork();
    }

}