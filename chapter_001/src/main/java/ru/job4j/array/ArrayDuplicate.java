package ru.job4j.array;

import java.util.Arrays;
/**
 * @author MShonorov (shonorov@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class ArrayDuplicate {
    /**
     * Removes duplicates from array.
     * @param array input array.
     * @return array with unique values.
     */
    public String[] remove(String[] array) {
        int newLength = array.length;
        for (int i = 0; i < newLength - 1; i++) {
            for (int j = i + 1; j < newLength - 1; j++) {
                if (array[i] == array[j]) {
                    String temp = array[j];
                    array[j] = array [newLength - 1];
                    array[newLength - 1] = temp;
                    newLength--;
                }
            }
        }
        return Arrays.copyOf(array, newLength);
    }
}
