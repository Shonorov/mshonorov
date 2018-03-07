package ru.job4j.chess;
/**
 * Class for a chess board.
 * @author MShonorov (shonorov@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class Board {

    private Figure[] figures = new Figure[32];
    private int figuresIndex = 0;
    /**
     * Adds new figure to the board.
     * @param figure new figure.
     */
    void add(Figure figure) {
        this.figures[figuresIndex++] = figure;
    }

    /**
     * Find figure in a given cell.
     * @param cell to search.
     * @return figure or null.
     */
    public Figure findByCell(Cell cell) {
        Figure result = null;
        for (int i = 0; i < figuresIndex; i++) {
            if (figures[i].position.getX() == cell.getX() && figures[i].position.getY() == cell.getY()) {
                result = figures[i];
            }
        }
        return result;
    }
    /**
     * Try to move figure from source to destination.
     * @param source figure.
     * @param dest cell.
     * @return true if move successful.
     * @throws ImpossibleMoveException when path is occupied.
     * @throws OccupiedWayException target cell is not empty.
     * @throws FigureNotFoundException no figure in the source field.
     */
    boolean move(Cell source, Cell dest) throws ImpossibleMoveException, OccupiedWayException, FigureNotFoundException {
        Figure figure = findByCell(source);
        if (figure == null) {
            throw new FigureNotFoundException("No figure in this cell!");
        }
        for (int i = 0; i < figuresIndex; i++) {
            if (figures[i].position.getX() == dest.getX() && figures[i].position.getY() == dest.getY()) {
                throw new OccupiedWayException("Target cell is occupied!");
            }
        }
        Cell[] figureWay = figure.way(source, dest);
        for (int i = 0; i < figureWay.length; i++) {
                if (findByCell(figureWay[i]) != null) {
                    throw new ImpossibleMoveException("Path is occupied!");
                }
        }
        figure = figure.copy(dest);
        return true;
    }
}
