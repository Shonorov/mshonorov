package ru.job4j.tracker;
/**
 * Items user actions interface.
 * @author MShonorov (shonorov@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public interface UserAction {

    int key();

    void execute(Input input, Tracker tracker);

    String info();

}
