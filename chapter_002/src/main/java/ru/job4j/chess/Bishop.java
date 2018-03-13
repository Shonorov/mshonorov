package ru.job4j.chess;

import java.util.Arrays;

/**
 * Abstract class for Bishop chess figure.
 * @author MShonorov (shonorov@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class Bishop extends Figure {

    public Bishop(Cell position) {
        super(position);
    }

    @Override
    Cell[] way(Cell source, Cell dest) throws ImpossibleMoveException {

        Cell[] path = new Cell[7];
        int moveX = source.getX();
        int moveY = source.getY();
        if (Math.abs(dest.getX() - source.getX()) != Math.abs(dest.getY() - source.getY())) {
            throw new ImpossibleMoveException("Bishop can not move like that!");
        }
        moveX = dest.getX() > moveX ? ++moveX : --moveX;
        moveY = dest.getY() > moveY ? ++moveY : --moveY;
        for (int i = 0; i < Math.abs(dest.getX() - source.getX()); i++) {
            path[i] = new Cell(moveX, moveY);
        }
        return Arrays.copyOf(path, Math.abs(dest.getX() - source.getX()));
    }

    @Override
    Figure copy(Cell dest) {
        return new Bishop(dest);
    }
}
