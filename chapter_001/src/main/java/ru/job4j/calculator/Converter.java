package ru.job4j.calculator;
/**
 * Корвертор валюты.
 */
public class Converter {
    /**
     * Money exchange rates.
     */
    private static final int EURO_EXCHANGE_RATE = 70;
    private static final int DOLLAR_EXCHANGE_RATE = 60;
    /**
     * Конвертируем рубли в евро.
     * @param value рубли.
     * @return Евро.
     */
    public int rubleToEuro(int value) {
        return value / EURO_EXCHANGE_RATE;
    }
    /**
     * Конвертируем рубли в доллары.
     * @param value рубли.
     * @return Доллоры
     */
    public int rubleToDollar(int value) {
        return value / DOLLAR_EXCHANGE_RATE;
    }
    /**
     * Dollar to rubles exchange converter.
     * @param value dollar.
     * @return rubles.
     */
    public int dollarToRuble(int value) {
        return value * DOLLAR_EXCHANGE_RATE;
    }
    /**
     * Euro to rubles exchange converter.
     * @param value euro.
     * @return rubles.
     */
    public int euroToRuble(int value) {
        return value * EURO_EXCHANGE_RATE;
    }
}