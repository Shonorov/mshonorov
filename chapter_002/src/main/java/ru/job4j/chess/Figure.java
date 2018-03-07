package ru.job4j.chess;
/**
 * Abstract class for chess figures.
 * @author MShonorov (shonorov@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public abstract class Figure {
    /**
     * Current figure position.
     */
    final Cell position;

    Figure(Cell position) {
        this.position = position;
    }
    /**
     * Get array with figure move cells.
     * @param source source cell.
     * @param dest dest cell.
     * @return array with figure move cells.
     * @throws ImpossibleMoveException if path is wrong.
     */
    abstract Cell[] way(Cell source, Cell dest) throws ImpossibleMoveException;
    /**
     * Creates new figure with given cell destination.
     * @param dest cell destination.
     * @return new figure.
     */
    abstract Figure copy(Cell dest);
}
