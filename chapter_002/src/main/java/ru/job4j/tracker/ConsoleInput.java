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
}
