package ru.job4j.bomberman;
import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Board field.
 * @author MShonorov (shonorov@gmail.com)
 * @version $Id$
 * @since 0.1
 */
@ThreadSafe
public class Field extends ReentrantLock {

    private int xPosition;
    private int yPosition;

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

    @GuardedBy("this")
    private Unit owner = null;

    public Unit getUnit() {
        return this.owner;
    }

    @Override
    public synchronized void unlock() {
        super.unlock();
        this.owner = null;
    }

    public synchronized void takeOwn(Unit unit) {
        this.owner = unit;
        super.lock();
    }
}
