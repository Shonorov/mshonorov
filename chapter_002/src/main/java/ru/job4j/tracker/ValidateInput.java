package ru.job4j.tracker;
/**
 * Console input validation.
 * @author MShonorov (shonorov@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class ValidateInput implements Input {

    private final Input input;

    public ValidateInput(final Input input) {
        this.input = input;
    }

    public String ask(String question) {
        return this.input.ask(question);
    }

    public int ask(String question, int[] range) {
        boolean invalid = true;
        int value = -1;
        do {
            try {
                value = this.input.ask(question, range);
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
