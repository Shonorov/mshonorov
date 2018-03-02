package ru.job4j.array;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
/**
 * @author MShonorov (shonorov@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class MergeArraysTest {

    @Test
    public void whenMergeTwoArrays() {
        MergeArrays merge = new MergeArrays();
        int[] array1 = new int[] {4, 7, 8, 9};
        int[] array2 = new int[] {1, 3, 5, 6, 7};
        int[] result = merge.merge(array1, array2);
        int[] expect = new int[] {1, 3, 4, 5, 6, 7, 7, 8, 9};
        assertThat(result, is(expect));
    }
}