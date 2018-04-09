package ru.job4j.bomberman;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Board field.
 * @author MShonorov (shonorov@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public final class Field extends ReentrantLock {

    private final int xPosition;
    private final int yPosition;

    public Field(int xPosition, int yPosition) {
        super();
        this.xPosition = xPosition;
        this.yPosition = yPosition;
    }

    public int getxPosition() {
        return xPosition;
    }

    public int getyPosition() {
        return yPosition;
    }
}
