package ru.job4j.chess;
/**
 * Abstract class for chess figures.
 * @author MShonorov (shonorov@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public abstract class Figure {

    final Cell position;

    public Figure(Cell position) {
        this.position = position;
    }

    Cell[] way(Cell source, Cell dest) throws ImpossibleMoveException {
        return null;
    }

    Figure copy(Cell dest) {
        return null;
    }
}
