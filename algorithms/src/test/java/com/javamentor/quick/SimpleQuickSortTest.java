package com.javamentor.quick;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SimpleQuickSortTest {

    @Test
    public void whenIntArrayThenSort() {
        SimpleQuickSort simpleQuickSort  = new SimpleQuickSort();
        int[] unsorted = {8, 0, -3, 5, 6, 9, 8, -4, 2, -99, 43};
        int[] result = simpleQuickSort.sort(unsorted);
        int[] expected = {-99, -4, -3, 0, 2, 5, 6, 8, 8, 9, 43};
        assertThat(result, is(expected));
    }
}