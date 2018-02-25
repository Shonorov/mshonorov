package ru.job4j.array;
/**
 * @author MShonorov (shonorov@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class Turn {
    /**
     * Turn array values order.
     * @param inArray input array.
     * @return output array.
     */
    public int[] back(int[] inArray) {
        for (int i = 0; i <= inArray.length / 2 - 1; i++) {
            int current = inArray[i];
            inArray[i] = inArray[inArray.length - i - 1];
            inArray[inArray.length - i - 1] = current;
        }
        return inArray;
    }
}
