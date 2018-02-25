package ru.job4j.array;

/**
 * @author MShonorov (shonorov@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class Square {

    /**
     * Fill array with its index squares.
     * @param bound array size.
     * @return array.
     */
    public int[] calculate(int bound) {

        int[] rst = new int[bound];
        // заполнить массив через цикл элементами от 1 до bound возведенные в квадрат
        for (int i = 0; i < bound; i++) {
            rst[i] = (i + 1) * (i + 1);
        }
        return rst;

    }
}
