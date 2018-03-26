package ru.job4j.thread;

import org.junit.Test;

public class StringThreadTest {

    @Test
    public void whenTwoThreadsThenWorkAsync() {
        StringThread stringThread = new StringThread(" Kiss my shiny metal ass! ");
        new Thread() {
            @Override
            public void run() {
                System.out.println("Spaces: " + stringThread.countSpaces());
            }
        }.start();
        new Thread() {
            @Override
            public void run() {
                System.out.println("Words: " + stringThread.countWords());
            }
        }.start();
    }
}
