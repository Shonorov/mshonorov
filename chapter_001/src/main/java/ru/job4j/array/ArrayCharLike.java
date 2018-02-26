package ru.job4j.array;
/**
 * Contains.
 * @author MShonorov (shonorov@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class ArrayCharLike {
    /**
     * Find if one string contains another.
     * @param origin origin string.
     * @param sub string to find.
     * @return true if contains.
     */
    public boolean contains(String origin, String sub) {
        boolean result = false;
        char[] data = origin.toCharArray();
        char[] value = sub.toCharArray();
        for (int i = 0; i <= data.length - value.length; i++) {
            for (int j = 0; j < value.length; j++) {
                if (data[i + j] != value[j]) {
                    break;
                }
                if (data[i + j] == value[value.length - 1]) {
                    result = true;
                }
            }
            if (result) {
                break;
            }
        }
        return result;
    }
}
