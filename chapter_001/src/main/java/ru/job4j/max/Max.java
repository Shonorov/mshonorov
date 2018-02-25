package ru.job4j.max;
/**
 * Max of two.
 * @author MShonorov (shonorov@gmail.com)
 */
public class Max {
    /**
     * Max of two ints.
     * @param first int.
     * @param second int.
     * @return max value.
     */
    public int max(int first, int second) {
        return first > second ? first : second;
    }
    /**
     * Max of three ints.
     * @param first int.
     * @param second int.
     * @param third int.
     * @return max value.
     */
    public int max(int first, int second, int third) {
        return max(max(first, second), third);
    }
}
