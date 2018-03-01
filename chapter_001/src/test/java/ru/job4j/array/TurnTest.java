package ru.job4j.array;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author MShonorov (shonorov@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class TurnTest {

    @Test
    public void whenTurnArrayWithEvenAmountOfElementsThenTurnedArray() {
        int[] inArray = new int[] {2, 6, 1, 4};
        int[] expect = new int[] {4, 1, 6, 2};
        Turn turn = new Turn();
        int[] result = turn.back(inArray);
        assertThat(result, is(expect));
    }

    @Test
    public void whenTurnArrayWithOddAmountOfElementsThenTurnedArray() {
        int[] inArray = new int[] {1, 2, 3, 4, 5};
        int[] expect = new int[] {5, 4, 3, 2, 1};
        Turn turn = new Turn();
        int[] result = turn.back(inArray);
        assertThat(result, is(expect));
    }
}
