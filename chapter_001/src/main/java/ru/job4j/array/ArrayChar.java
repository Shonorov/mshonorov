package ru.job4j.array;
/**
 * Обертка над строкой.
 * @author MShonorov (shonorov@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class ArrayChar {
    private char[] data;

    public ArrayChar(String line) {
        this.data = line.toCharArray();
    }
    /**
     * Проверяет, что слово начинается с префикса.
     * @param prefix префикс.
     * @return если слово начинаеться с префикса
     */
    public boolean startWith(String prefix) {
        boolean result = true;
        char[] value = prefix.toCharArray();
        for (int i = 0; i < value.length; i++) {
                if (this.data[i] != value[i]) {
                    result = false;
                    break;
                }
        }
        return result;
    }
}
