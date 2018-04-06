package ru.job4j.bomberman;

import java.util.Random;

/**
 * Game board.
 * @author MShonorov (shonorov@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class Board {

    private Field[][] board;
    private int boardSize;
    private int[] moves = {-1, 0, 1};

    public Board(int size) {
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
        if (!board[startX][startY].isLocked()) {
            unit.takeOwn(board[startX][startY]);
            result = true;
        } else {
            System.out.println("Unit start field " + startX + " : " + startY + " is occupied!");
        }
        return result;
    }

    /**
     * Choose next random step.
     * @param value current position.
     * @return move position.
     */
    public int randomMove(int value) {
        int result = value + moves[new Random().nextInt(moves.length)];
        if (result < 0) {
            result++;
        } else if (result >= boardSize){
            result--;
        }
        return result;
    }

    /**
     * Move unit to the next random position.
     * @param unit to move.
     */
    private void move(Unit unit) {
        int nextX = randomMove(unit.getXPosition());
        int nextY = randomMove(unit.getYPosition());
        while (!unit.go(board[nextX][nextY])) {
            nextX = randomMove(unit.getXPosition());
            nextY = randomMove(unit.getYPosition());
        }

        System.out.println(unit.getName() + " moved to the " + nextX + " : " + nextY);
    }

    /**
     * Start unit movement.
     * @param unit to move.
     */
    public synchronized void start(Unit unit) {
        while (!Thread.currentThread().isInterrupted()) {
            move(unit);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
