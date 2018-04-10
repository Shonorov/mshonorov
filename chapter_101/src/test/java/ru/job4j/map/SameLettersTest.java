package ru.job4j.map;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * @author MShonorov (shonorov@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class SameLettersTest {

    @Test
    public void whenSameWordsThenTrue() {
        SameLetters same = new SameLetters();
        boolean result = same.compare("Hello", "Hello");
        assertThat(result, is(true));
    }

    @Test
    public void whenAnagramThenTrue() {
        SameLetters same = new SameLetters();
        boolean result = same.compare("Старорежимность", "Нерасторжимость");
        assertThat(result, is(true));
    }

    @Test
    public void whenNotSameWordsThenTrue() {
        SameLetters same = new SameLetters();
        boolean result = same.compare("Another", "Word");
        assertThat(result, is(false));
    }

    @Test
    public void whenEmptyWordThenTrue() {
        SameLetters same = new SameLetters();
        boolean result = same.compare("Another", "");
        assertThat(result, is(false));
    }
}
