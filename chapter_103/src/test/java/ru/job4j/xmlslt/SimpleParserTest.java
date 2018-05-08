package ru.job4j.xmlslt;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.job4j.xmlxslt.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**
 * @author MShonorov (shonorov@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class SimpleParserTest {

    private final PrintStream stdout = System.out;
    private final ByteArrayOutputStream out = new ByteArrayOutputStream();

    /**
     * Stores output from the console to ByteArray.
     * @Before runs method before test.
     */
    @Before
    public void loadOutput() {
        System.setOut(new PrintStream(this.out));
    }
    /**
     * Returns output from ByteArray back to the console.
     * @After runs method after test.
     */
    @After
    public void backOutput() {
        System.setOut(this.stdout);
    }

    @Test
    public void whenCount10ThenSum55() {
        SimpleParser parser = new SimpleParser("temp.db", 10);
        parser.start();
        assertThat(new String(this.out.toByteArray()), is("55"));
    }
}
