package ru.job4j.array;
/**
 * @author MShonorov (shonorov@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class FindLoop {
    /**
     * Return index of given element in array.
     * @param data array.
     * @param el element.
     * @return index.
     */
    public int indexOf(int[] data, int el) {
        int rsl = -1;
        for (int index = 0; index < data.length; index++) {
            if (data[index] == el) {
                rsl = index;
                break;
            }
        }
        return rsl;
    }
}
