package ru.job4j.thread;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.lessThan;

import org.junit.Test;
/**
 * @author MShonorov (shonorov@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class CountCharTest {
    @Test
    public void whenParentStopThenChildStop() {
        String string = "Bla bla bla Bla bla bla Bla bla bla Bla bla bla Bla bla bla Bla bla bla Bla bla bla Bla bla bla";
        CountChar countChar = new CountChar(string);
        Thread count = new Thread(countChar);
        Thread stop = new Thread(new Time(50, count));
        count.start();
        stop.start();
        try {
            count.join();
            stop.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertThat(countChar.getSize(), lessThan(string.length()));
    }
}
