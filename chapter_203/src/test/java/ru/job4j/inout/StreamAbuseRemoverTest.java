package ru.job4j.inout;

import org.junit.Test;

import java.io.*;

import static org.junit.Assert.*;

public class StreamAbuseRemoverTest {

    @Test
    public void whenStreamContainsWordThenTrue() {
        StreamAbuseRemover streamAbuseRemover = new StreamAbuseRemover();
        OutputStream outputStream = new ByteArrayOutputStream(10);
        InputStream inputStream = new ByteArrayInputStream("I will not abuse the system anymore ever".getBytes());
        String[] words = {"not", "anymore"};
        streamAbuseRemover.dropAbuses(inputStream, outputStream, words);
        String result = ((ByteArrayOutputStream) outputStream).toString();
        String expect = "I will abuse the system ever";
        assertEquals(result, expect);
    }

}