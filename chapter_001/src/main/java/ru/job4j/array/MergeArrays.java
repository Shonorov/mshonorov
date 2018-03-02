package ru.job4j.array;
/**
 * Merge two sorted arrays to another sorted one.
 * @author MShonorov (shonorov@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class MergeArrays {
    /**
     * Merge arrays.
     * @param array1 sorted 1.
     * @param array2 sorted 2.
     * @return result array.
     */
    public int[] merge(int[] array1, int[] array2) {
        int[] result = new int[array1.length + array2.length];
        int position1 = 0;
        int position2 = 0;
        for (int i = 0; i < result.length; i++) {
            int current1 = value(position1, array1);
            int current2 = value(position2, array2);
            if (current1 < current2) {
                result[i] = array1[position1++];
            } else if (current1 > current2) {
                result[i] = array2[position2++];
            } else {
                result[i++] = array1[position1++];
                result[i] = array2[position2++];
            }
        }
        return result;
    }
    /**
     * Get current value for array.
     * @param position in array.
     * @param array to merge.
     * @return current value or max int if all elements are merged.
     */
    private int value(int position, int[] array) {
        int result = Integer.MAX_VALUE;
        if (position < array.length) {
            result = array[position];
        }
        return result;
    }

}
