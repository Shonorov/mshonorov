package ru.job4j.array;
/**
 * @author MShonorov (shonorov@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class Matrix {
    /**
     * Fills matrix of fixed size with multiplied indexes.
     * @param size matrix size.
     * @return matrix.
     */
    int[][] multiple(int size) {
        int[][] matrix = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                matrix[i][j] = (i + 1) * (j + 1);
            }
        }
        return matrix;
    }
}
