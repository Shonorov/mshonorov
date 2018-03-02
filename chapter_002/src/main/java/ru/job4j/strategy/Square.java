package ru.job4j.strategy;
/**
 * Square class.
 * @author MShonorov (shonorov@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class Square implements Shape {

    @Override
    public String draw() {
        StringBuilder pic = new StringBuilder();
        pic.append("++++").append(System.lineSeparator());
        pic.append("+  +").append(System.lineSeparator());
        pic.append("+  +").append(System.lineSeparator());
        pic.append("++++");
        return pic.toString();
    }
}
