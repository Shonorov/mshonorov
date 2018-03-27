package ru.job4j.thread;
/**
 * Count char in the string.
 * @author MShonorov (shonorov@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class CountChar implements Runnable {

    private String string;
    private int size;

    public CountChar(String string) {
        this.string = string;
    }

    public int getSize() {
        return size;
    }

    /**
     * Count char in the string
     * @return char count.
     */
    private int countChar() {
        int size = 0;
        do {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            size++;
        }
        while (!Thread.interrupted() && size < string.length());
        return size;
    }

    @Override
    public void run() {
        this.size = countChar();
    }
}
