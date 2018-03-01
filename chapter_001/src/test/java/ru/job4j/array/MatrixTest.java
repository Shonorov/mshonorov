package ru.job4j.array;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
/**
 * @author MShonorov (shonorov@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class MatrixTest {

    @Test
    public void whenIndex9And9x8ThenReturn72() {
        Matrix matrix = new Matrix();
        int[][] result = matrix.multiple(9);
        int expected9x8 = 72;
        assertThat(result[8][7], is(expected9x8));
    }

    @Test
    public void whenIndex9And5x6ThenReturn30() {
        Matrix matrix = new Matrix();
        int[][] result = matrix.multiple(9);
        int expected5x6 = 30;
        assertThat(result[4][5], is(expected5x6));
    }
}
