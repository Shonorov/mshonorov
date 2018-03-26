package ru.job4j.thread;
/**
 * Multithreaded string parsing.
 * @author MShonorov (shonorov@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class StringThread extends Thread {
    /**
     * Input string.
     */
    private String string;

    public StringThread(String string) {
        this.string = string;
    }

    /**
     * Count spaces in the string.
     * @return number of spaces.
     */
    public int countSpaces() {
        int result = 0;
        for (int i = 0; i < string.length(); i++) {
            if (string.charAt(i) == ' ') {
                result++;
            }
        }
        return result;
    }
    /**
     * Count words in the string.
     * @return number of words.
     */
    public int countWords() {
        int result = 0;
        boolean newWord = false;
        int eol = string.length() - 1;
        for (int i = 0; i <= eol; i++) {
            if (string.charAt(i) != ' ' && i != eol) {
                newWord = true;
            } else if (string.charAt(i) == ' ' && newWord) {
                newWord = false;
                result++;
            } else if (string.charAt(i) != ' ' && i == eol) {
                result++;
            }
        }
        return result;
    }
}
