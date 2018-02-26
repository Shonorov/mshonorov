package ru.job4j.array;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author MShonorov (shonorov@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class ArrayCharLikeTest {
    @Test
    public void whenContainsThenTrue() {
        ArrayCharLike word = new ArrayCharLike();
        boolean result = word.contains("Hello", "ell");
        assertThat(result, is(true));
    }

    @Test
    public void whenNotContainsThenFalse() {
        ArrayCharLike word = new ArrayCharLike();
        boolean result = word.contains("Hello", "hi");
        assertThat(result, is(false));
    }
}

