package ru.job4j.search;
/**
 * @author MShonorov (shonorov@gmail.com)
 * @version $Id$
 * @since 0.1
 */
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ConvertListTest {

    @Test
    public void whenToListThenList() {
        ConvertList converter = new ConvertList();
        int[][] source = new int[][] {{1, 2, 3}, {4, 5, 6}, {7, 0, 0}};
        List<Integer> expect = new LinkedList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 0, 0));
        List<Integer> result = converter.toList(source);
        assertThat(result, is(expect));
    }

    @Test
    public void whenToArrayThenArray() {
        ConvertList converter = new ConvertList();
        List<Integer> source = new LinkedList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7));
        int[][] expect = new int[][] {{1, 2, 3}, {4, 5, 6}, {7, 0, 0}};
        int[][] result = converter.toArray(source, 3);
        assertThat(result, is(expect));
    }

    @Test
    public void  whenConvertThenArray() {
        ConvertList converter = new ConvertList();
        List<int[]> source = new ArrayList<>(Arrays.asList(new int[]{1, 2}, new int[]{3, 4, 5, 6}));
        List<Integer> expect = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6));
        assertThat(converter.convert(source), is(expect));
    }

}
