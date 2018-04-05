package ru.job4j.bomberman;

import java.util.concurrent.locks.ReentrantLock;
import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

/**
 * Game board.
 * @author MShonorov (shonorov@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class Board {
    ReentrantLock[][] board = new ReentrantLock[BOARD_SIZE][BOARD_SIZE];
    private static final int BOARD_SIZE = 8;

    public void init() {
        Hero hero = new Hero();
    }
}
