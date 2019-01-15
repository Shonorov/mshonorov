package com.javamentor.merge;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class SimpleMergeSortTest {

    @Test
    public void whenIntArrayThenSort() {
        SimpleMergeSort simpleMergeSort = new SimpleMergeSort();
        int[] unsorted = {8, 0, -3, 5, 6, 9, 8, -4, 2, -99, 43};
        int[] result = simpleMergeSort.sort(unsorted);
        int[] expected = {-99, -4, -3, 0, 2, 5, 6, 8, 8, 9, 43};
        assertThat(result, is(expected));
    }
}