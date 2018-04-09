package ru.job4j.bomberman;

import java.util.Random;

/**
 * Bomberman game board.
 * @author MShonorov (shonorov@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class Board {
    /**
     * Game field.
     * Board size.
     * Available unit moves.
     */
    private final Field[][] board;
    private final int boardSize;
    private final int[] moves = {-1, 0, 1};

    public Board(final int size) {
        this.boardSize = size;
        board = new Field[size][size];
        fillBoard();
    }

    /**
     * Fill board with fields.
     */
    private void fillBoard() {
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                board[i][j] = new Field(i, j);
            }
        }
    }
    /**
     * Place Unit to the board.
     * @param unit to put.
     * @param startX x position.
     * @param startY y position.
     * @return true if success.
     */
    public boolean initUnit(Unit unit, int startX, int startY) {
        boolean result = false;
        if (unit.takeOwn(board[startX][startY])) {
            result = true;
            start(unit);
        }
        return result;
    }

    /**
     * Choose next random step.
     * @param value current position.
     * @return move position.
     */
    private int randomMove(int value) {
        int result = value + moves[new Random().nextInt(moves.length)];
        if (result < 0) {
            result++;
        } else if (result >= boardSize) {
            result--;
        }
        return result;
    }

    /**
     * Get next not the same field.
     * @param x current X position.
     * @param y current Y position.
     * @return next field to move.
     */
    private Field randomField(int x, int y) {
        int resultx;
        int resulty;
        do {
            resultx = randomMove(x);
            resulty = randomMove(y);
        } while (resultx == x && resulty == y);
        return board[resultx][resulty];
    }
    /**
     * Move unit to the next random position.
     * @param unit to move.
     */
    private void move(Unit unit) {
        Field next = randomField(unit.getXPosition(), unit.getYPosition());
        Field previous = next;
        while (!unit.go(next)) {
            while (previous.equals(next)) {
                next = randomField(unit.getXPosition(), unit.getYPosition());
            }
        }
    }
    /**
     * Start unit movement.
     * @param unit to move.
     */
    private void start(Unit unit) {
        while (!Thread.currentThread().isInterrupted()) {
            move(unit);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(Thread.currentThread().getName() + " interrupted!");
    }
}
