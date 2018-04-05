package ru.job4j.bomberman;

import java.util.Random;
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

    private Field[][] board;
    private int boardSize;

    public Board(int size) {
        this.boardSize = size;
        board = new Field[boardSize][boardSize];
        fillBoard();
    }

    private void fillBoard() {
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                board[i][j] = new Field(i, j);
            }
        }
    }

    public boolean initUnit(Unit unit, int startX, int startY) {
        boolean result = false;
        if (!board[startX][startY].isLocked()) {
            board[startX][startY].takeOwn(unit);
            result = true;
        }
        return result;
    }
    //TODO zero move
    private int randomMove(int value) {
        int result = new Random().nextBoolean() ? value + 1 : value - 1;
        if (result < 0) {
            result += 2;
        } else if (result >= boardSize){
            result -= 2;
        }
        return result;
    }

    private void move(Unit unit) {
        unit.go(board[randomMove(unit.getXPosition())][unit.getYPosition()]);
    }

    public void start(Unit unit) {
        while (!Thread.currentThread().isInterrupted()) {
            move(unit);
            try {
                wait(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
