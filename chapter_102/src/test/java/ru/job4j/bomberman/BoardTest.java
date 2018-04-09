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
        Board board = new Board(2);
        Unit player1 = new Hero("Player1");
        Unit player2 = new Hero("Player2");
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                board.initUnit(player1, 0, 0);
            }
        });
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                board.initUnit(player2, 1, 1);
            }
        });
        thread1.start();
        thread2.start();
        try {
            Thread.sleep(3000);
//            thread1.join();
//            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
