package ru.job4j.coffe;

import java.util.Arrays;
/**
 * Coffee machine money changer.
 * @author MShonorov (shonorov@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class CoffeeMachine {
    /**
     * Coin amounts.
     */
    private static final int[] COINS = new int[] {10, 5, 2, 1};
    /**
     * Gives change with less possible coins count.
     * @param value money.
     * @param price price.
     * @return array of coins.
     */
    public int[] changes(int value, int price) {
        if (price > value) {
            return null;
        }
        int change = value - price;
        int[] result = new int[change];
        int position = 0;
        for (int i = 0; i < COINS.length; i++) {
            int count = change / COINS[i];
            for (int j = 0; j < count; j++) {
                result[position++] = COINS[i];
            }
            change -= COINS[i] * count;
        }
        return Arrays.copyOf(result, position);
    }
}