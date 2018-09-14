package ru.job4j.inout;

import org.apache.commons.lang.RandomStringUtils;
import org.junit.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.charset.Charset;
import java.util.Random;

import static org.junit.Assert.*;

public class BigFileSorterTest {

    @Test
    public void whenReadThenSort() throws IOException {
        BigFileSorter sorter = new BigFileSorter();
        sorter.sort(new File("C://test//big.txt"), new File("C://test//sorted.txt"));

    }

//    @Test
    public void generateFile() throws IOException {
        File source = new File("C://test//big.txt");
        Writer writer = new FileWriter(source);
        Random rnd = new Random();
        for (int i = 0; i < 10000; i++) {
            int length = rnd.nextInt(200);
            String generatedString = RandomStringUtils.randomAlphabetic(length);
            writer.write(generatedString + System.lineSeparator());
        }
    }

}