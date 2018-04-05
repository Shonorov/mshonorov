package ru.job4j.bomberman;
//enum Type {
//    PLAYER, MONSTER
//}

import java.util.concurrent.TimeUnit;

/**
 * Game unit.
 * @author MShonorov (shonorov@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public abstract class Unit {

    private String name;
    private Field field;

    public Unit(String name) {
        this.name = name;
    }

    public int getXPosition() {
        return field.getxPosition();
    }

    public int getYPosition() {
        return field.getyPosition();
    }

    public boolean go(Field field) {
        boolean result = false;
        try {
            field.tryLock(500, TimeUnit.MILLISECONDS);
            result = true;
        } catch (InterruptedException e) {
            System.out.println("Field " + field + " is locked");
        }
        this.field = field;
        return result;
    }
}
