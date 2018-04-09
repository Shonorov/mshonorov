package ru.job4j.bomberman;

import java.util.concurrent.TimeUnit;
import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;
/**
 * Game unit.
 * @author MShonorov (shonorov@gmail.com)
 * @version $Id$
 * @since 0.1
 */
@ThreadSafe
public abstract class Unit {

    private String name;
    @GuardedBy("this")
    private Field field;

    public Unit(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public synchronized int getXPosition() {
        return field.getxPosition();
    }

    public synchronized int getYPosition() {
        return field.getyPosition();
    }

    /**
     * Try move Unit to the field.
     * @param targetField to move to.
     * @return true if not locked.
     */
    public synchronized boolean go(Field targetField) {
        boolean result = false;
        Field current = field;
        if (takeOwn(targetField)) {
            current.unlock();
            result = true;
        }
        return result;
    }

    /**
     * Try to take ownership of the field.
     * @param field to lock.
     * @return true if success.
     */
    public synchronized boolean takeOwn(Field field) {
        boolean result = false;
        try {
            if (field.tryLock(500, TimeUnit.MILLISECONDS)) {
                this.field = field;
                result = true;
                System.out.println(name + " moved to the " + field.getxPosition() + " : " + field.getyPosition());
            } else {
                result = false;
                System.out.println(name + " " + field.getxPosition() + " : " + field.getyPosition() + " is locked!");
            }
        } catch (InterruptedException e) {
            System.out.println("Field " + field + " is locked");
        }
        return result;
    }
}
