package ru.job4j.bomberman;

import org.junit.Test;

/**
 * @author MShonorov (shonorov@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class BoardTest {

    @Test
    public void whenTest() {
        Board board = new Board(8);
        Unit player = new Hero("Player1");
        board.initUnit(player, 0, 0);
    }

}
