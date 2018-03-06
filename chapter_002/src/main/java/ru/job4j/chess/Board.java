package ru.job4j.chess;
/**
 * Class for chess board.
 * @author MShonorov (shonorov@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class Board {

    Figure[] figures = new Figure[32];

    void add(Figure figure) {

    }

    boolean move(Cell source, Cell dest) throws ImpossibleMoveException, OccupiedWayException, FigureNotFoundException {
        return true;

//        - Что в заданной ячейки есть фигура. если нет. то выкинуть исключение
//                - Если фигура есть. Проверить может ли она так двигаться. Если нет то упадет исключение
//                - Проверить что полученный путь. не занят фигурами. Если занят выкинуть исключение
//        - Если все отлично. Записать в ячейку новое новое положение Figure figure.copy(Cell dest)
    }
}
