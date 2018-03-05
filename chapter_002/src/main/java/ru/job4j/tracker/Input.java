package ru.job4j.tracker;
/**
 * Console input management interface.
 * @author MShonorov (shonorov@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public interface Input {

    String ask(String question);

    int ask(String question, int[] range);
}
