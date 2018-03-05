package ru.job4j.tracker;
/**
 * Starts tracker UI.
 * @author MShonorov (shonorov@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class StartUI {

    private final Input input;

    public StartUI(Input input) {
        this.input = input;
    }
    /**
     * Initiates and manages project menu.
     */
    public void init() {
        Tracker tracker = new Tracker();
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
        Input input = new ValidateInput(new ConsoleInput());
        new StartUI(input).init();
    }
}