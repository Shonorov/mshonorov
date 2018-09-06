package ru.job4j.inout;

import static org.junit.Assert.*;

import org.junit.Test;
import java.io.ByteArrayInputStream;

public class EvenFindStreamTest {

    @Test
    public void whenStreamHasEvenThenTrue() {
        EvenFindStream findStream = new EvenFindStream();
        byte[] array = new byte[]{1, 3, 5, 6, 7, 9};
        try (ByteArrayInputStream out = new ByteArrayInputStream(array)) {
            assertTrue(findStream.isNumber(out));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void whenStreamHasOddThenFalse() {
        EvenFindStream findStream = new EvenFindStream();
        byte[] array = new byte[]{1, 3, 5, 7, 9};
        try (ByteArrayInputStream out = new ByteArrayInputStream(array)) {
            assertFalse(findStream.isNumber(out));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}