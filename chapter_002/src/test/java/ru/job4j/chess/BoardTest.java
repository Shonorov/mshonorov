package ru.job4j.chess;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertThat;
/**
 * @author MShonorov (shonorov@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class BoardTest {

    @Test
    public void whenFindByCellThenReturnFigure() {
        Board board = new Board();
        Cell cell = new Cell(3, 3);
        Figure bishop = new Bishop(cell);
        board.add(bishop);
        assertThat(board.findByCell(cell), is(bishop));
    }

    @Test
    public void whenMoveThenSuccess() {
        Board board = new Board();
        Cell cell = new Cell(5, 5);
        Figure bishop = new Bishop(cell);
        board.add(bishop);
        assertThat(board.move(cell, new Cell(4, 4)), is(true));
    }

    @Test (expected = ImpossibleMoveException.class)
    public void whenMoveThenWrongWay() {
        Board board = new Board();
        Cell cell = new Cell(5, 5);
        Figure bishop = new Bishop(cell);
        board.add(bishop);
        board.move(cell, new Cell(4, 3));
    }

    @Test (expected = FigureNotFoundException.class)
    public void whenFigureNotFoundThenException() {
        Board board = new Board();
        Cell cell = new Cell(5, 5);
        Figure bishop = new Bishop(cell);
        board.add(bishop);
        board.move(new Cell(4, 4), new Cell(6, 6));
    }

    @Test (expected = OccupiedWayException.class)
    public void whenDestOccupiedThenException() {
        Board board = new Board();
        Cell cellFrom = new Cell(5, 5);
        Cell cellTo = new Cell(4, 4);
        board.add(new Bishop(cellFrom));
        board.add(new Bishop(cellTo));
        board.move(cellFrom, cellTo);
    }

    @Test (expected = ImpossibleMoveException.class)
    public void whenFigureOnTheWayThenException() {
        Board board = new Board();
        Cell cellFrom = new Cell(5, 5);
        Cell cellTo = new Cell(4, 4);
        board.add(new Bishop(cellFrom));
        board.add(new Bishop(cellTo));
        board.move(cellFrom, new Cell(3, 3));
    }

    @Test
    public void whenMoveThenFigureNotTheSame() {
        Board board = new Board();
        Cell cellFrom = new Cell(5, 5);
        Figure bishop = new Bishop(cellFrom);
        Cell cellTo = new Cell(4, 4);
        board.add(bishop);
        assertThat(bishop, not(board.findByCell(cellTo)));
    }
}
