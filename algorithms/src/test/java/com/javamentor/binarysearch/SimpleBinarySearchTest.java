package com.javamentor.binarysearch;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SimpleBinarySearchTest {

    @Test
    public void whenSearchArrayThenReturnPositionOtherMinusOne() {
        SimpleBinarySearch simpleMergeSort = new SimpleBinarySearch();
        int[] array = {-99, -4, -3, 0, 2, 5, 6, 8, 9, 43};
        assertThat(simpleMergeSort.search(array, -99), is(0));
        assertThat(simpleMergeSort.search(array, 43), is(9));
        assertThat(simpleMergeSort.search(array, 8), is(7));
        assertThat(simpleMergeSort.search(array, 1), is(-1));
    }
}