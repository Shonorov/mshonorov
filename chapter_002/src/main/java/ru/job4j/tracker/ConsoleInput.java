package ru.job4j.tracker;

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
     * @param question text showed to user.
     * @return one line entered from console by user.
     */
    public String ask(String question) {
        System.out.println(question);
        return scanner.nextLine();
    }

    public int ask(String question, int[] range) {
        int key = Integer.valueOf(this.ask(question));
        boolean exist = false;
        for (int value : range) {
            if(value == key) {
                exist = true;
                break;
            }
        }
        if (exist) {
          return key;
        } else {
            throw new MenuOutException("Value out of menu range.");
        }
    }
}
