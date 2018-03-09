package ru.job4j.search;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
/**
 * List management.
 * @author MShonorov (shonorov@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class ConvertList {
    /**
     * Convert two dimensional array to list.
     * @param array two dimensional array.
     * @return list.
     */
    public List<Integer> toList(int[][] array) {
        List<Integer> result = new ArrayList<>();
        for (int[] subArray : array) {
            for (int data : subArray) {
                result.add(data);
            }
        }
        return result;
    }
    /**
     * Convert list to two dimensional array.
     * @param list source list.
     * @param rows rows in result array.
     * @return two dimensional array.
     */
    public int[][] toArray(List<Integer> list, int rows) {
        int lines = (int) Math.ceil(list.size() / (float) rows);
        int[][] result = new int[rows][lines];
        Iterator<Integer> iter = list.iterator();
        for (int i = 0; i < lines; i++) {
            for (int j = 0; j < rows; j++) {
                if (iter.hasNext()) {
                    result[i][j] = iter.next();
                } else {
                    result[i][j] = 0;
                }
            }
        }
        return result;
    }
    /**
     * Convert list of arrays to list.
     * @param list list of arrays.
     * @return list.
     */
    public List<Integer> convert(List<int[]> list) {
        List<Integer> result = new ArrayList<>();
        for (int[] subArray : list) {
            for (int data : subArray) {
                result.add(data);
            }
        }
        return result;
    }
}
