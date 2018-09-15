package ru.job4j.inout;

import org.apache.commons.lang.RandomStringUtils;
import org.junit.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Random;

import static org.junit.Assert.*;

public class BigFileSorterTest {

    private static final String SRC = "C://windows//temp//big.txt";
    private static final String DST = "C://windows//temp//sorted.txt";
    private static final int BIG_LINES = 1000;
    private static final int BIG_LENGTH = 200;

    @Test
    public void whenGenerateSourceFileThenExists() {
        File source = new File(SRC);
        try (Writer writer = new FileWriter(source)) {
            Random rnd = new Random();
            for (int i = 0; i < BIG_LINES; i++) {
                int length = rnd.nextInt(BIG_LENGTH);
                String generatedString = RandomStringUtils.randomAlphabetic(length);
                writer.write(generatedString + System.lineSeparator());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertTrue(source.exists());
    }

    @Test
    public void whenReadAndSortThenSameLength() {
        BigFileSorter sorter = new BigFileSorter();
        File source = new File(SRC);
        File target = new File(DST);
        sorter.sort(source, target);
        assertEquals(source.length(), target.length());
    }

}