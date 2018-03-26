package ru.job4j.thread;
/**
 * Count char in th string.
 * @author MShonorov (shonorov@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class CountChar implements Runnable {

    private String string;
    private Thread current = Thread.currentThread();

    public CountChar(String string) {
        this.string = string;
    }

    private int countChar() {
        char[] array = string.toCharArray();
        int size = 0;
        for (int i = 0; i < array.length; i++) {
            if (current.isInterrupted()) {
                System.out.println("Interrupted!");
                break;
            }
            System.out.println(array[i]);
            size++;
        }
        return size;
    }

    @Override
    public void run() {
        System.out.println(countChar());
    }
}
