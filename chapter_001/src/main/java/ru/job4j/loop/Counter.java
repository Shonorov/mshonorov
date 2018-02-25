package ru.job4j.loop;
/**
 * @author MShonorov (shonorov@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class Counter {
    /**
     * Get sum of evens in range.
     * @param start of the range.
     * @param finish of the range.
     * @return sum.
     */
    public int add(int start, int finish) {
        int sum = 0;
        for (int i = start; i <= finish; i++) {
            if (i % 2 == 0) {
                sum += i;
            }
        }
        return sum;
    }
}
