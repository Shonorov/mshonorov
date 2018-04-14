package ru.job4j.loop;

import java.util.function.BiPredicate;
/**
 * Построить пирамиду в псевдографике.
 * В коментариях код до рефакторинга для истории.
 * @author MShonorov (shonorov@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class Paint {
    /**
     * Уникальный метод для рефакторинга.
     * BiPredicate<Integer, Integer> - дженерик.
     * @param height pyramid height.
     * @param weight pyramid weight.
     * @param predict predicate.
     * @return string with pyramid.
     */
    private String loopBy(int height, int weight, BiPredicate<Integer, Integer> predict) {
        StringBuilder screen = new StringBuilder();
        for (int row = 0; row != height; row++) {
            for (int column = 0; column != weight; column++) {
                if (predict.test(row, column)) {
                    screen.append("^");
                } else {
                    screen.append(" ");
                }
            }
            screen.append(System.lineSeparator());
        }
        return screen.toString();
    }

    /**
     * Print right triangle.
     * @param height triangle height.
     * @return string with triangle.
     */
    public String rightTrl(int height) {
        return this.loopBy(
                height,
                height,
                (row, column) -> row >= column
        );
    }
    /**
     * Print left triangle.
     * @param height triangle height.
     * @return string with triangle.
     */
    public String leftTrl(int height) {
        return this.loopBy(
                height,
                height,
                (row, column) -> row >= height - column - 1
        );
    }
    /**
     * Paint whole pyramid
     * @param height pyramid height.
     * @return StringBuilder.
     */
    public String pyramid(int height) {
        return this.loopBy(
                height,
                2 * height - 1,
                (row, column) -> row >= height - column - 1 && row + height - 1 >= column
        );
    }
}