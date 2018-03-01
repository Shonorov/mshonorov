package ru.job4j.array;
import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
/**
 * @author MShonorov (shonorov@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class FindLoopTest {

    @Test
    public void whenElementPresentInArray() {
        FindLoop findLoop = new FindLoop();
        int[] array = new int[] {1, 5, 3, 4, 10, 15};
        int element = 10;
        int result = findLoop.indexOf(array, element);
        int expect = 4;
        assertThat(result, is(expect));
    }

    @Test
    public void whenElementNotPresentInArray() {
        FindLoop findLoop = new FindLoop();
        int[] array = new int[] {1, 5, 3, 4, 10, 15};
        int element = 20;
        int result = findLoop.indexOf(array, element);
        int expect = -1;
        assertThat(result, is(expect));
    }

}


