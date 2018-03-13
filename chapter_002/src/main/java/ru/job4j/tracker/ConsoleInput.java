package ru.job4j.tracker;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Console input management.
 * @author MShonorov (shonorov@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class ConsoleInput implements Input {

    private Scanner scanner = new Scanner(System.in);
    /**
     * Prompts input from user.
     * @param question text shown to user.
     * @return one line entered from console by user.
     */
    public String ask(String question) {
        System.out.println(question);
        return scanner.nextLine();
    }

    /**
     * Prompts input from user and throws exception.
     * @param question text shown to user.
     * @param range array of menu indexes.
     * @return number of line entered from console by user.
     */
    public int ask(String question, ArrayList<Integer> range) {
        int key = Integer.valueOf(this.ask(question));
        boolean exist = false;
        for (int value : range) {
            if (value == key) {
                exist = true;
                break;
            }
        }
        if (!exist) {
            throw new MenuOutException("Value out of menu range.");
        }
        return key;
    }
}