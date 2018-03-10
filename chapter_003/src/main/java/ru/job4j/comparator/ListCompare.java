package ru.job4j.comparator;
/**
 * Custom string comparator.
 * @author MShonorov (shonorov@gmail.com)
 * @version $Id$
 * @since 0.1
 */
import java.util.Comparator;

public class ListCompare implements Comparator<String> {

    @Override
    public int compare(String left, String right) {
        int result = 0;
        char[] lefts = left.toCharArray();
        char[] rights = right.toCharArray();
        int position = 0;
        while (lefts[position] != rights[position]) {
            result = lefts[position] < rights[position] ? -1 : 1;
            position++;
        }
        if (result == 0 && lefts.length != rights.length) {
            result = lefts.length < rights.length ? -1 : 1;
        }
        return result;
    }
}
