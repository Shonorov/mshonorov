package ru.job4j.xmlslt;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.Test;
import ru.job4j.xmlxslt.SQLiteStorage;

/**
 * @author MShonorov (shonorov@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class SQLiteStorageTest {

    @Test
    public void whenJDBCWriteThenSelect() {
        SQLiteStorage storage = new SQLiteStorage("temp.db", 10);
        int[] expect = new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int[] result = storage.process();
        assertThat(result, is(expect));
    }
}
