package ru.job4j.loop;
/**
 * @author MShonorov (shonorov@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class Factorial {
    /**
     * Get factorial of n.
     * @param n numbers.
     * @return factorial.
     */
    public int calc(int n) {
        int factorial = 1;
        for (int i = 1; i <= n; i++) {
            factorial *= i;
        }
        return  factorial;
    }
}
