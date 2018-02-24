package ru.job4j.loop;

import java.util.function.BiPredicate;

/**
 * Построить пирамиду в псевдографике.
 * @author MShonorov (shonorov@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class Paint {

    /**
     * Уникальный метод для рефакторинга.
     * BiPredicate<Integer, Integer> - дженерик.
     * @param height
     * @param weight
     * @param predict
     * @return
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
     * Paint right side of pyramid
     * @param height
     * @return StringBuilder.
     */
//    public String rightTrl(int height) {
//        // Буфер для результата.
//        StringBuilder screen = new StringBuilder();
//        // ширина будет равна высоте.
//        int weight = height;
//        // внешний цикл двигается по строкам.
//        for (int row = 0; row != height; row++) {
//            // внутренний цикл определяет положение ячейки в строке.
//            for (int column = 0; column != weight; column++) {
//                // если строка равна ячейки, то рисуем галку.
//                // в данном случае строка определяем, сколько галок будет на строке
//                if (row >= column) {
//                    screen.append("^");
//                } else {
//                    screen.append(" ");
//                }
//            }
//            // добавляем перевод строки.
//            screen.append(System.lineSeparator());
//        }
//        // Получаем результат.
//        return screen.toString();
//    }

    public String rightTrl(int height) {
        return this.loopBy(
                height,
                height,
                (row, column) -> row >= column
        );
    }

    /**
     * Paint left side of pyramid
     * @param height
     * @return StringBuilder.
     */
//    public String leftTrl(int height) {
//        StringBuilder screen = new StringBuilder();
//        int weight = height;
//        for (int row = 0; row != height; row++) {
//            for (int column = 0; column != weight; column++) {
//                if (row >= weight - column - 1) {
//                    screen.append("^");
//                } else {
//                    screen.append(" ");
//                }
//            }
//            screen.append(System.lineSeparator());
//        }
//        return screen.toString();
//    }

    public String leftTrl(int height) {
        return this.loopBy(
                height,
                height,
                (row, column) -> row >= height - column - 1
        );
    }

    /**
     * Paint whole pyramid
     * @param height
     * @return StringBuilder.
     */
//    public String pyramid(int height) {
//        StringBuilder screen = new StringBuilder();
//        int weight = 2 * height - 1;
//        for (int row = 0; row != height; row++) {
//            for (int column = 0; column != weight; column++) {
//                if (row >= height - column - 1 && row + height - 1 >= column) {
//                    screen.append("^");
//                } else {
//                    screen.append(" ");
//                }
//            }
//            screen.append(System.lineSeparator());
//        }
//        return screen.toString();
//    }

    public String pyramid(int height) {
        return this.loopBy(
                height,
                2 * height - 1,
                (row, column) -> row >= height - column - 1 && row + height - 1 >= column
        );
    }
}