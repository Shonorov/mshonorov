package ru.job4j.tracker;

import java.util.TreeSet;

/**
 * Starts tracker UI.
 * @author MShonorov (shonorov@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class StartUI {

    private final Input input;
    private final Tracker tracker;

    public StartUI(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }
    /**
     * Initiates and manages project menu.
     */
    public void init() {
        MenuTracker menu = new MenuTracker(this.input, tracker);
        menu.fillActions();
        do {
            menu.show();
            menu.select(String.valueOf(input.ask("Select menu item:", menu.getMenuIndex())));
        } while (!"y".equals(this.input.ask("Exit? ( y / n )")));
    }
    /**
     * Starts tracker exrcution.
     * @param args input arguments.
     */
    public static void main(String[] args) {

        try (Tracker tracker = new Tracker("config.properties")) {
            Input input = new ValidateInput(new ConsoleInput());
            new StartUI(input, tracker).init();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}