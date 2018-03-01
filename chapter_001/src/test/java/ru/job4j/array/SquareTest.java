package ru.job4j.array;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
/**
 * @author MShonorov (shonorov@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class SquareTest {

    @Test
    public void whenBound10ThenLast100() {
        Square square = new Square();
        int[] result = square.calculate(10);
        int expect = 100;
        assertThat(result[9], is(expect));
    }
}
