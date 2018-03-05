package ru.job4j.tracker;
/**
 * Console input validation.
 * @author MShonorov (shonorov@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class ValidateInput extends ConsoleInput {

    private final Input input;

    public ValidateInput(final Input input) {
        this.input = input;
    }

    public int ask(String question, int[] range) {
        boolean invalid = true;
        int value = -1;
        do {
            try {
                value = super.ask(question, range);
                invalid = false;
            } catch (MenuOutException moe) {
                System.out.println("Please enter menu number in a given range.\r\n");
                        } catch (NumberFormatException nfe) {
                System.out.println("Please enter menu number in numeric format.\r\n");
            }
        } while (invalid);
        return value;
    }
}
