package ru.job4j.thread;

import org.junit.Test;

public class StringThreadTest {

    @Test
    public void whenTwoThreadsThenWorkAsync() {
        System.out.println("Start!");
        StringThread stringThread = new StringThread(" Kiss my shiny metal ass! ");
        Thread countSpaces = new Thread() {
            @Override
            public void run() {
                System.out.println("Spaces: " + stringThread.countSpaces());
            }
        };
        Thread countWords = new Thread() {
            @Override
            public void run() {
                System.out.println("Words: " + stringThread.countWords());
            }
        };
        countSpaces.start();
        countWords.start();
        try {
            countSpaces.join();
            countWords.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Finish!");
    }
}
